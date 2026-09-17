# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
./gradlew :desktopApp:run                    # Run desktop app
./gradlew :desktopApp:hotRun                 # Run desktop with hot reload
./gradlew lintKotlin                         # ktlint check (CI gate)
./gradlew formatKotlin                       # ktlint auto-fix
./gradlew :composeApp:testAndroidHostTest    # Unit tests (CI gate) — covers commonTest too
./gradlew :composeApp:desktopTest            # Same commonTest, run on the desktop JVM target
./gradlew :composeApp:iosSimulatorArm64Test  # Same commonTest, run on the iOS simulator (macOS only)
```

Run a single test class or method with the standard Gradle filter:

```bash
./gradlew :composeApp:testAndroidHostTest --tests "feature.agent.presentation.AgentsListViewModelTest"
./gradlew :composeApp:desktopTest --tests "*AgentsListUseCaseTest.getFactionsList*"
```

Android builds are flavored `Dev` / `Live` (`assembleDevDebug`, `bundleLiveRelease`, …). Desktop
picks its flavor from the `VARIANT` environment variable instead; anything else defaults to `Dev`.

## Architecture

### Module topology

Three Gradle modules, but only one holds code: **`:composeApp`** is the KMP module containing every
feature, the design system, DI, networking, and persistence. `:androidApp`, `:desktopApp` and
`iosApp/` are thin platform shells (entry point, platform DI bindings, packaging config) that depend
on it. Adding a feature means adding a package under `composeApp/src/commonMain/kotlin/feature/`,
not a new Gradle module.

### Feature layering

Each `feature/<name>/` is self-contained and internally layered:

```
feature/<name>/
├── presentation/   *Screen.kt, *Content, *ViewModel.kt, *Action.kt
├── domain/         *UseCase.kt
├── data/           repository/, database/, mapper/
├── model/          *Response.kt (DTO), *State.kt (UI state), domain models
└── components/     feature-local Composables
```

Cross-feature code lives at the top level: `network/`, `database/`, `datastore/`, `di/`, `ui/`,
`utils/`, `root/`.

### MVI, and where navigation actions are handled

A screen is split into a **stateful `*Screen`** (resolves the ViewModel via `koinViewModel()`,
collects `uiState`) and a **stateless `*Content`** (takes `uiState` + `onAction`). The non-obvious
part is that `*Screen` **intercepts navigation actions before they reach the ViewModel** and routes
them to nav lambdas instead:

```kotlin
onAction = { action ->
    when (action) {
        is AgentsListAction.ClickAgent -> onAgentClick(action.agentId)
        AgentsListAction.ClickBack -> onBackClick()
        else -> viewModel.onAction(action)
    }
}
```

So navigation-flavoured branches inside a ViewModel's `onAction` are unreachable — they exist only
to keep the `when` exhaustive. ViewModels never touch the NavController.

State is a single `data class *State` in `model/`, held in a `MutableStateFlow`, exposed via
`stateIn(viewModelScope, SharingStarted.WhileSubscribed(15000L), …)` with initial work kicked off in
`.onStart { }`.

### Data flow: Room is the source of truth

Repositories do **not** return network responses to the UI. The pattern is:

1. `requestAndUpdate<X>DB()` fetches from Ktor and writes entities into Room, returning `Result<Unit>`.
2. `get<X>()` returns a `Flow` straight from the DAO, mapped entity → domain model.
3. The UI observes that Flow; a refresh is a write to Room, which re-emits.

`UpdateDatabaseUseCase` (in `database/`) is the cross-feature entry point ViewModels call to trigger
a refresh.

There are **three separate Room databases**, not one with several DAOs: `AgentsListDB`,
`CoverImagesListDB`, `HoYoLabAccountDB`, each built in `databaseModule` and each with its own folder
under `composeApp/schemas/`. `AgentsListDB` uses `fallbackToDestructiveMigration(true)` (it is
re-downloadable cache); the other two do not.

### Networking

One interface + `Impl` pair per remote source in `network/` (`ZzzHttp`, `OfficialWebHttp`,
`PixivHttp`, `HoYoLabHttp`, `GoogleDocHttp`, `ForumHttp`), each with its own `HttpClient` built by a
factory function in `ZzzHttpClientFactory.kt`. Engines are injected per platform (OkHttp on
Android/desktop, Darwin on iOS) from `platformModule`.

Game data is not a real API: it is JSON committed to the separate **`mrfatworm/ZZZ-Archive-Asset`**
repo and fetched from `raw.githubusercontent.com`. The branch is chosen by build variant — `Live`
reads that repo's `main`, `Dev` reads its `dev` — via `ZzzConfig.API_PATH` / `ASSET_PATH`, generated
by the BuildConfig plugin in `composeApp/build.gradle.kts`.

### DI (Koin)

`initKoin()` in `di/InitKoin.kt` registers six modules: `platformModule` (an `expect val`,
`actual` per platform), `databaseModule`, `dataStoreModule`, `repositoryModule`, `useCaseModule`,
`viewModelModule`. Each platform shell calls `initKoin` at startup. ViewModels are obtained in
Composables with `koinViewModel()`; constructor injection everywhere else. The two DataStores are
distinguished by Koin qualifiers `named("PreferenceDataStore")` / `named("ConfigDataStore")`.

### Design system

**Do not use `MaterialTheme` colors/typography.** The app provides its own `AppTheme` object backed
by `staticCompositionLocalOf` — `AppTheme.colors`, `.typography`, `.shape`, `.spacing`, `.size`,
`.adaptiveLayoutType`, `.contentType`, `.themeController`. All of it is installed by
`ZzzArchiveTheme` in `ui/theme/Theme.kt`, which also hosts the global `SnackbarHost` and overrides
`LocalUriHandler` with a snackbar-aware safe handler.

`ThemeController` carries user-adjustable `isDark`, `fontScale` and `uiScale` (both scales clamped
0.5–2.0). `provideTypography(fontScale)` and `provideSize(uiScale)` re-derive tokens from them, so
new tokens must be added there rather than hardcoded — otherwise they ignore the user's scale
setting.

Shared Composables live in `ui/components/<category>/` (buttons, cards, chips, dialogs, items,
navigation) and are prefixed `Zzz*` when they wrap a Material 3 component.

### Adaptive layout

`AdaptiveLayout()` maps the window size class onto two enums in `ui/utils/WindowsStateUtils.kt`:
`AdaptiveLayoutType` (Compact / Medium / Expanded) drives navigation chrome — `MainContainer` shows
a `ZzzArchiveNavigationRail` at Medium+ and a bottom bar at Compact — while `ContentType`
(Single / Dual) drives list-detail. Screens that behave differently are split into explicit
`*ScreenSingle` / `*ScreenDual` files rather than branching inline.

Every screen is expected to handle all three widths.

### Navigation

Type-safe Navigation Compose. Destinations are `@Serializable` members of the `Screen` sealed
interface (`ui/navigation/Screen.kt`); top-level tabs are `MainFlow` entries that each name a
`startScreen`. Graphs are nested: `RootNavGraph` → `MainNavGraph` → per-area graphs in
`ui/navigation/graph/app/`. `NavActions` wraps the controller so screens never see it directly.

### Resources and localization

Compose Resources under `composeApp/src/commonMain/composeResources/` — a single `strings.xml` per
locale (`values`, `values-zh`, `values-zh-rCN`, `values-ja`), plus `drawable/` and `font/`. The
`Language` enum in `utils/Language.kt` maps app languages to both the resource code and the
`officialCode` used when querying HoYoverse endpoints; `changePlatformLanguage` is an `expect fun`.

## Testing

Every test lives in **`commonTest`** — Repository, UseCase, mapper *and* ViewModel — so all three
platforms run the same suite. There are no mocks: hand-written `Fake*` classes live next to the
tests they serve (`FakeZzzHttp`, `FakeAgentListDao`, `FakeAgentRepository`, …). Reuse the existing
Fake instead of reaching for a mocking library.

Because UseCases are concrete classes rather than interfaces, a ViewModel test builds the *real*
UseCase on top of Fake repositories, and asserts against the repository's own state instead of
verifying calls. Only three collaborators are faked at the UseCase level, because their production
implementations are platform-bound: `LanguageUseCase`, `AppInfoUseCase` and `AppActionsUseCase`.

ViewModels need a `Dispatchers.Main`, which desktop and iOS do not have by default. Test classes
extend **`MainDispatcherTest`** (`commonTest/kotlin/MainDispatcherTest.kt`), the multiplatform
replacement for the old JUnit 4 `MainDispatcherRule`; it installs an `UnconfinedTestDispatcher` from
its `init` block so the ViewModel can be built in a subclass property initializer.

`testAndroidHostTest` compiles and runs `commonTest`, so it is a sufficient local gate, but CI runs
`testAndroidHostTest` + `desktopTest` on Linux and `iosSimulatorArm64Test` on macOS.
`desktopTest` and `iosTest` add only a placeholder test each of their own.

## Conventions

- New Kotlin files carry the MIT copyright header block used throughout the repo (present in 311 of
  357 files; nothing enforces it, so it drifts).
- ktlint via kotlinter, configured in `.editorconfig`: `android_studio` style, 120-column limit,
  function signatures forced multiline at 2+ parameters, `@Composable` exempt from function naming.
  `ignoreLintFailures = false` — lint failures break the build.
- Version catalog (`gradle/libs.versions.toml`) is the only place versions are declared;
  `zzzVersionName` / `zzzVersionCode` there drive every platform's packaging.
- `main` is the development branch, `release/x.x.x` are release branches, and hotfixes pushed to
  `release/**` auto-forward-port to `main` via `.github/workflows/forward-port-hotfix.yml`.
- **Finishing a change: squash merge straight into `main`** — one feature, one clean commit. The
  `squash-merge` skill holds that flow (gate → squash → push → delete branch). Pull requests are
  reserved for outside contributions and for changes that genuinely need review; the PR process in
  [CONTRIBUTING.md](CONTRIBUTING.md) is written for contributors, not for the maintainer's own work.
- Commit messages follow Conventional Commits (see [CONTRIBUTING.md](CONTRIBUTING.md)).
