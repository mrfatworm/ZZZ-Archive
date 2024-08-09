ZZZ Archive 是一個絕區零的維基應用程式，
主要目標為桌面端 Windows, macOS ，同時 Android, iOS 也將盡可能相容

ZZZ Archive is a Wiki application for “Zenless Zone Zero”.
The main targets are desktop platforms (Windows, macOS),
but it will also aim to be compatible with Android and iOS as much as possible.

---

## Current Status / 目前狀態
🟢 In Design (Figma)

## How to run on desktop / 如何執行桌面板
Android Studio > Run > Edit Configurations > New > Gradle
> desktopRun -DmainClass=MainKt --quiet

## Planning / 未來計畫
- 🚧 Home Screen
- 🚧 Agents Screen
- 🚧 W-Engines Screen
- 🚧 Drivers Screen
- 🚧 Bangboo Screen
- 🚧 Setting Screen
- 🚧 Feedback Screen
- 🚧 Sync from HoYoLab

## Tech Stack / 使用技術
- ✅ Multi-Platform: KMP (Kotlin Multi-Platform)
- ✅ Shared UI: CMP (Compose Multi-Platform)
- ✅ RWD Layout ([by chrisbanes](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform))
- ✅ Nested Navigation
- ✅ Koin DI
- ✅ Localization(English, Chinese Traditional)
- ✅ Ktor Network
- 🚧 Design System (Base on Material 3)
- 🚧 Room Database or SQLDelight

## Special Thanks / 鳴謝
* Resource from Zenless Zone Zero
* [JetBrains KMP Guideline](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
* Android Open Source Project
* [chrisbanes/material3-windowsizeclass-multiplatform](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform)