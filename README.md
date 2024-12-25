[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![ComposeMultiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.7.3-blue.svg?style=flat)](https://www.jetbrains.com/compose-multiplatform/)

![ZZZArchiveBanner](docs/screenshot/github_cover.webp)

README Language: [中文](/docs/readme/README_CHT.md) | [English](/README.md)

## A Multiplatform Wiki for Zenless Zone Zero

ZZZ Archive is a companion app for the action game Zenless Zone Zero, where users can explore in-game information and browse popular fan-made creations.

## Adaptive Layout

<img src="/docs/screenshot/adaptive_layout_demo.gif" alt="ZZZArchiveBanner"  width="1080px">

## Download

<a href="https://play.google.com/store/apps/details?id=com.mrfatworm.zzzarchive"><img alt="Get it on Google Play" src="docs/screenshot/img_google_play.webp" height="48px"/></a>
<a href="https://github.com/mrfatworm/ZZZ-Archive/releases/latest"><img alt="Get apk on GitHub" src="docs/screenshot/img_github_apk.webp" height="48px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (iOS)" src="docs/screenshot/img_app_store_ios.webp" height="48px"/></a>
<a href="https://apps.microsoft.com/detail/9p5h3ccmzl9z"><img alt="Get it on Microsoft Store" src="docs/screenshot/img_microoft_store.webp" height="48px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (macOS)" src="docs/screenshot/img_app_store_mac.webp" height="48px"/></a>

## UI Design
<a href="https://www.figma.com/community/file/1441663496302710815/zzz-archive"><img alt="Screen Flow Chart (Figma Community)" src="docs/screenshot/img_figma_screen_flow_chart.webp" height="64px"/></a>

## Kanban (Realtime)
<a href="https://www.figma.com/design/j8DMjEOYnDhlDrablx4JYZ/Kanban-ZZZ-Archive?node-id=0-1&t=sLbk3v7npmSm1ZLc-1"><img alt="Kanban (Figma Community)" src="docs/screenshot/img_figma_kanban.webp" height="64px"/></a>

## Design System

![DesignSystem](docs/screenshot/img_design_system.webp)

## Running on Desktop

1. [Set up your environment](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html)
2. In Android Studio, go to **Run** > **Edit Configurations** > **New** > **Gradle**.
3. Configure as follows:
  - Run: `desktopRun -DmainClass=MainKt --quiet`
  - Environment Variables: `VARIANT=Dev`

## Library

Thanks to all the contributors who made KMP/CMP possible!
- Kotlin Multiplatform (KMP)
- Compose Multiplatform (CMP)
- JetBrains Adaptive Layout
- JetBrains Navigation Compose
- JetBrains Lifecycle ViewModel
- Androidx Room Database
- Androidx DataStore
- Koin
- Ktor
- Coil
- BuildKonfig
- Okio I/O

## Localization Support

- English
- Traditional Chinese

## Special Thanks

- Resources from [Zenless Zone Zero](https://zenless.hoyoverse.com/) and [Zenless Zone Zero Wiki](https://zenless-zone-zero.fandom.com/wiki/Zenless_Zone_Zero_Wiki)
- [Philipp Lackner’s YouTube Channel](https://www.youtube.com/@PhilippLackner)
- [JetBrains KMP Guidelines](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Android Developer Guidelines](https://developer.android.com/)
- [Android Open Source Project](https://github.com/android)
- [Compose Multiplatform Wizard](https://github.com/terrakok/Compose-Multiplatform-Wizard)
- [BuildKonfig for KMP](https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/)
- [Release macOS App](https://www.marcogomiero.com/posts/2024/compose-macos-app-store/)
- [Design System](https://github.com/felipecastilhos)