[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![ComposeMultiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.7.1-blue.svg?style=flat)](https://www.jetbrains.com/compose-multiplatform/)

![ZZZArchiveBanner](./screenshot/github_cover.webp)

# A Multiplatform Wiki for Zenless Zone Zero

**ZZZ Archive** is a Zenless Zone Zero Wiki app, built with Compose Multiplatform to deliver a smooth and consistent experience across platforms. ZZZ Archive lets players access detailed information on characters, weapons, Bangboo, and Drive Disc from the game, as well as explore popular second creations by fans.

**ZZZ Archive** 是 "絕區零" 的維基應用程式，透過 Compose Multiplatform 打造跨平台一致的使用體驗。用戶可查詢角色、武器、邦布和驅動光碟資訊，並且瀏覽熱門的二創作品。

---
### Adaptive Layout
![ZZZArchiveBanner](./screenshot/adaptive_layout_demo.gif)

## Download

<a href="https://play.google.com/store/apps/details?id=com.mrfatworm.zzzarchive"><img alt="Get it on Google Play" src="screenshot/img_google_play.webp" height="56px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (iOS)" src="screenshot/img_app_store_ios.webp" height="56px"/></a>
<a href="https://apps.microsoft.com/detail/9p5h3ccmzl9z"><img alt="Get it on Microsoft Store" src="screenshot/img_microoft_store.webp" height="56px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (macOS)" src="screenshot/img_app_store_mac.webp" height="56px"/></a>

## UI Design
<a href="https://www.figma.com/community/file/1441663496302710815/zzz-archive"><img alt="Screen Flow Chart (Figma Community)" src="screenshot/img_figma_screen_flow_chart.webp" height="64px"/></a>

## Kanban (Realtime)
<a href="https://www.figma.com/design/j8DMjEOYnDhlDrablx4JYZ/Kanban-ZZZ-Archive?node-id=0-1&t=sLbk3v7npmSm1ZLc-1"><img alt="Kanban (Figma Community)" src="screenshot/img_figma_kanban.webp" height="64px"/></a>

## Running on Desktop / 桌面端執行方法

1. [Set up your environment](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html)
2. In Android Studio, go to **Run** > **Edit Configurations** > **New** > **Gradle**.
3. Configure as follows:
  - Run: `desktopRun -DmainClass=MainKt --quiet`
  - Environment Variables: `VARIANT=Dev`

## Tech Stack / 使用技術

- **Kotlin Multiplatform (KMP)**
- **Compose Multiplatform (CMP)**
- Adaptive Layout for dynamic screen adjustment
- Compose Navigation
- ViewModel Lifecycle Management (JetBrains Lifecycle ViewModel)
- Koin for Dependency Injection
- Ktor for Networking
- Localization support (English and Traditional Chinese)
- Full-screen edge-to-edge layout
- **Design System**
  - Custom UI Components
  - Dark/Light Color Schemes
  - Noto Sans Typography
  - Spacing and Radius adjustments
- BuildKonfig for environment configurations

## Special Thanks / 鳴謝

- Resources from [Zenless Zone Zero](https://zenless.hoyoverse.com/) and [Zenless Zone Zero Wiki](https://zenless-zone-zero.fandom.com/wiki/Zenless_Zone_Zero_Wiki)
- [Philipp Lackner’s YouTube Channel](https://www.youtube.com/@PhilippLackner)
- [JetBrains KMP Guidelines](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Android Developer Guidelines](https://developer.android.com/)
- [Android Open Source Project](https://github.com/android)
- [Compose Multiplatform Wizard](https://github.com/terrakok/Compose-Multiplatform-Wizard)
- [BuildKonfig for KMP](https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/)
- [Release macOS App](https://www.marcogomiero.com/posts/2024/compose-macos-app-store/)
- Cover Artist [EDIBLE](https://www.pixiv.net/users/75576278)