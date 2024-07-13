ZZZ Archive 是一個絕區零的維基應用程式，也是我第一個 Kotlin 跨平台的專案，
主要目標為桌面端 Windows, macOS ，行動裝置 Android, iOS 也將盡可能相容

ZZZ Archive is a Wiki application for the game “Zenless Zone Zero”.
It is also my first Kotlin multiplatform project. 
The main targets are desktop platforms (Windows, macOS),
but it will also aim to be compatible with mobile devices (Android, iOS) as much as possible.

---

## 目前狀態 / Current Status
🟢 In Design (Figma)

## 如何執行桌面板 / How to run on desktop
Android Studio > Run > Edit Configurations > New > Gradle
> desktopRun -DmainClass=MainKt --quiet

## 未來計畫 / Planning
- 🚧  Home Screen
- 🚧  Agents Screen
- 🚧  W-Engines Screen
- 🚧  Drivers Screen
- 🚧  Bangboo Screen
- 🚧  Setting Screen
- 🚧  Feedback Screen
- 🚧  Sync from HoYoLab

## 使用技術 / Tech Stack
- ✅ Shared UI: Jetpack Compose
- ✅ RWD Layout ([by chrisbanes](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform))
- ✅ Nested Navigation
- ✅ Koin DI
- 🚧  Localization(zh-TW, zh-CN, en-US)
- 🚧  Design System (Base on Material 3)
- 🚧  Ktor Network
- 🚧  Room Database or SQLDelight

## 鳴謝 / Special Thanks
* Resource from Zenless Zone Zero
* [JetBrains KMP Guideline](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
* Android Open Source Project
* [chrisbanes/material3-windowsizeclass-multiplatform](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform)