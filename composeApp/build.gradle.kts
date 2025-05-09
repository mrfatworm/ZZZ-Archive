import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import java.util.Properties
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    jvmToolchain(17)
    androidTarget {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class) instrumentedTestVariant.sourceSetTree.set(
            KotlinSourceSetTree.test
        )
    }

    jvm("desktop")

    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "com.mrfatworm.zzzarchive")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
            implementation(libs.compose.adaptive)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.coil.network.ktor)
            implementation(libs.bundles.ktor)
            implementation(libs.coil.compose)
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.okio)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.cryptography.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.androidx.datastore.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlin.test)
            @OptIn(ExperimentalComposeLibrary::class) implementation(compose.uiTest)
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.cryptography.provider.jdk)
        }

        androidUnitTest.dependencies {
            implementation(libs.mockk)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.cryptography.provider.apple)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.cryptography.provider.jdk)
        }

        dependencies {
            ksp(libs.androidx.room.compiler)
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }
    }
}


val zzzVersionName = "Luciana 2025.03.14"
val bundleVersionName = "1.2.0"
val zzzVersionCode = 6
val zzzPackageId = "com.mrfatworm.zzzarchive"

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 35

    defaultConfig {
        applicationId = zzzPackageId
        minSdk = 26
        targetSdk = 35
        versionCode = zzzVersionCode
        versionName = zzzVersionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("variant")
    productFlavors {
        create("Dev") {
            dimension = "variant"
            applicationIdSuffix = ".dev"
            versionNameSuffix = " Beta"
            resValue("string", "app_name_variant", "ZZZ Archive-Beta")
        }

        create("Live") {
            isDefault = true
            dimension = "variant"
            resValue("string", "app_name_variant", "ZZZ Archive")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        val desktopPackageName: String
        val desktopPackageId: String
        if ((System.getenv("VARIANT") ?: "") == "Live") {
            desktopPackageName = "ZZZ Archive"
            desktopPackageId = zzzPackageId
        } else {
            desktopPackageName = "ZZZ Archive Dev"
            desktopPackageId = "$zzzPackageId.dev"
        }

        val isAppStoreRelease = project.property("macOsAppStoreRelease").toString().toBoolean()

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Pkg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = desktopPackageName
            packageVersion = bundleVersionName
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
            }
        }
    }
}

// Ref: https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/
project.extra.set("buildkonfig.flavor", currentBuildVariant())
val localProperties = project.rootProject.file("local.properties")
val aesKey: String =
    Properties().apply { load(localProperties.inputStream()) }.getProperty("AES_KEY")
        ?: "eryuQ00pQZ16die2sfaPerkoGwQVM9jXACLNAMPHM/M=" // Fake key for open-source

buildkonfig {
    packageName = zzzPackageId
    objectName = "ZzzConfig"
    exposeObjectWithName = "ZzzConfig"

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset"
        )
        buildConfigField(
            FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api"
        )
        buildConfigField(FieldSpec.Type.STRING, "VERSION", "$zzzVersionName-Beta")
        buildConfigField(FieldSpec.Type.STRING, "AES_KEY", aesKey)
    }

    defaultConfigs("Dev") {
        buildConfigField(
            FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset"
        )
        buildConfigField(
            FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api"
        )
        buildConfigField(FieldSpec.Type.STRING, "VERSION", "$zzzVersionName-Beta")
        buildConfigField(FieldSpec.Type.STRING, "AES_KEY", aesKey)
    }

    defaultConfigs("Live") {
        buildConfigField(
            FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Asset"
        )
        buildConfigField(
            FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Api"
        )
        buildConfigField(FieldSpec.Type.STRING, "VERSION", zzzVersionName)
        buildConfigField(FieldSpec.Type.STRING, "AES_KEY", aesKey)
    }
}

fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("Dev", "Live")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    val variant = if (matcher.find()) matcher.group(1) else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

private fun Project.currentBuildVariant(): String {
    val variants = setOf("Dev", "Live")
    return getAndroidBuildVariantOrNull() ?: System.getenv()["VARIANT"].toString()
        .takeIf { it in variants } ?: "Dev"
}
