ZZZ Archive is a Zenless Zone Zero wiki application developed using cross-platform: Compose Multiplatform.

ZZZ Archive 是一個絕區零的維基應用程式，使用跨平台技術: Compose Multiplatform 開發

---
## Multiplatform / 跨平台
- ✅ Android
- ✅ iOS
- ✅ macOS
- ✅ Windows

## UI Design
🟢 Design by Figma
File will publish to Community in future

## How to run on desktop / 如何執行桌面板
Android Studio > Run > Edit Configurations > New > Gradle
Run: `desktopRun -DmainClass=MainKt --quiet`
Env Variables: `VARIANT=Dev`

## Tech Stack / 使用技術
- KMP (Kotlin Multiplatform)
- CMP (Compose Multiplatform)
- JetBrains Adaptive Layout
- JetBrains Navigation Compose
- JetBrains Lifecycle ViewModel
- Koin DI
- Ktor Network
- Localization(en-us, zh-tw)
- Full Screen Layout (edge-to-edge)
- Design System
  - UI Component
  - Color Scheme (Dark, Light)
  - Typographic (Noto Sans)
  - Spacer
  - Radius
- BuildKonfig

## Special Thanks / 鳴謝
* Resource from Zenless Zone Zero
* [JetBrains KMP Guideline](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
* Android Open Source Project
* [Compose Multiplatform Wizard](https://github.com/terrakok/Compose-Multiplatform-Wizard)
* [BuildKonfig for KMP](https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/)
