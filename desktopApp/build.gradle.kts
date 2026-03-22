import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.composeApp)

    implementation(compose.desktop.currentOs)
    implementation(libs.compose.ui)
    implementation(libs.compose.components.resources)
    implementation(libs.kotlinx.coroutines.swing)
    implementation(libs.cryptography.provider.jdk)
    implementation(libs.compose.uiToolingPreview)
}
val zzzPackageId = libs.versions.zzzPackageId.get()
val isLive = providers.environmentVariable("VARIANT").orNull == "Live"
val desktopPackageId = if (isLive) zzzPackageId else "$zzzPackageId.dev"
val macExtraPlistKeys: String
    get() = """
      <key>ITSAppUsesNonExemptEncryption</key>
      <false/>
    """.trimIndent()

compose.desktop {
    application {
        mainClass = "MainKt"

        val isAppStoreRelease = project.property("macOsAppStoreRelease").toString().toBoolean()

        nativeDistributions {
            modules("jdk.unsupported")
            if (isAppStoreRelease) {
                appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
            }
            targetFormats(TargetFormat.Dmg, TargetFormat.Pkg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = desktopPackageId
            packageVersion = libs.versions.zzzVersionName.get()
            description = "Zenless Zone Zero Wiki App"
            copyright = "© 2024 mrfatworm. All rights reserved."
            linux {
                iconFile.set(project.file("desktopLogo/Logo.png"))
            }
            windows {
                iconFile.set(project.file("desktopLogo/Logo.ico"))
            }
            // Ref: https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/
            macOS {
                iconFile.set(project.file("desktopLogo/Logo.icns"))
                bundleID = desktopPackageId
                signing {
                    sign.set(true)
                    identity.set("JHAN CHENG LI")
                }
                minimumSystemVersion = "12.0"
                appStore = isAppStoreRelease

                if (isAppStoreRelease) {
                    provisioningProfile.set(project.file("config/macos/embedded.provisionprofile"))
                    runtimeProvisioningProfile.set(project.file("config/macos/runtime.provisionprofile"))
                    entitlementsFile.set(project.file("config/macos/entitlements.plist"))
                    runtimeEntitlementsFile.set(project.file("config/macos/runtime-entitlements.plist"))
                }

                infoPlist {
                    extraKeysRawXml = macExtraPlistKeys
                }
            }
        }
    }
}
