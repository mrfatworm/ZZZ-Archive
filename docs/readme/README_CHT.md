[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![ComposeMultiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.7.1-blue.svg?style=flat)](https://www.jetbrains.com/compose-multiplatform/)

![ZZZArchiveBanner](/docs/screenshot/github_cover.webp)

README 文件語言: [中文](/docs/readme/README_CHT.md) | [English](/README.md)

# 跨平台絕區零維基應用程式

ZZZ Archive 是動作遊戲 "絕區零" 的維基應用程式，使用者可查詢遊戲中相關資料、瀏覽熱門的二創作品。

### 響應式設計

![ZZZArchiveBanner](/docs/screenshot/adaptive_layout_demo.gif)

## 商店下載

<a href="https://play.google.com/store/apps/details?id=com.mrfatworm.zzzarchive"><img alt="Get it on Google Play" src="docs/screenshot/img_google_play.webp" height="56px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (iOS)" src="docs/screenshot/img_app_store_ios.webp" height="56px"/></a>
<a href="https://apps.microsoft.com/detail/9p5h3ccmzl9z"><img alt="Get it on Microsoft Store" src="docs/screenshot/img_microoft_store.webp" height="56px"/></a>
<a href="https://apps.apple.com/tw/app/zzz-archive/id6738107658"><img alt="Get it on App Store (macOS)" src="docs/screenshot/img_app_store_mac.webp" height="56px"/></a>

## UI 設計

<a href="https://www.figma.com/community/file/1441663496302710815/zzz-archive"><img alt="Screen Flow Chart (Figma Community)" src="docs/screenshot/img_figma_screen_flow_chart.webp" height="64px"/></a>

## 即時進度看板

<a href="https://www.figma.com/design/j8DMjEOYnDhlDrablx4JYZ/Kanban-ZZZ-Archive?node-id=0-1&t=sLbk3v7npmSm1ZLc-1"><img alt="Kanban (Figma Community)" src="docs/screenshot/img_figma_kanban.webp" height="64px"/></a>

## 設計系統

![DesignSystem](/docs/screenshot/img_design_system.webp)

## 桌面端執行方法

1. [Set up your environment](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html)
2. 在 Android Studio 中, 選擇上排選單 **Run** > **Edit Configurations** > **New** > **Gradle**.
3. 設定如下:
  - Run: `desktopRun -DmainClass=MainKt --quiet`
  - Environment Variables: `VARIANT=Dev`

## 使用套件

- Kotlin Multiplatform (KMP)
- Compose Multiplatform (CMP)
- JetBrains Adaptive Layout
- JetBrains Navigation Compose
- JetBrains Lifecycle ViewModel
- Androidx Room Database
- Koin
- Ktor
- Coil
- BuildKonfig
- Multiplatform Setting
- Okio I/O

## 多語系支援

- 英文
- 繁體中文

## 銘謝

- [Zenless Zone Zero](https://zenless.hoyoverse.com/)與 [Zenless Zone Zero Wiki](https://zenless-zone-zero.fandom.com/wiki/Zenless_Zone_Zero_Wiki)的圖形與資源
- [Philipp Lackner’s YouTube Channel](https://www.youtube.com/@PhilippLackner)
- [JetBrains KMP Guidelines](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Android Developer Guidelines](https://developer.android.com/)
- [Android Open Source Project](https://github.com/android)
- [Compose Multiplatform Wizard](https://github.com/terrakok/Compose-Multiplatform-Wizard)
- [BuildKonfig for KMP](https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/)
- [Release macOS App](https://www.marcogomiero.com/posts/2024/compose-macos-app-store/)
- 封面繪師 [EDIBLE](https://www.pixiv.net/users/75576278)