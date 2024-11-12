import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvmToolchain(17)
    androidTarget {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
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
            implementation(libs.multiplatformSettings.no.arg)
        }
        commonTest.dependencies {
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlin.test)
            implementation(libs.multiplatformSettings.test)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.core.splashscreen)
        }

        androidUnitTest.dependencies {
            implementation(libs.mockk)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
val zzzVersionName = "Luciana 2024.11"
val windowsVersionName = "1.0.0"
val zzzPackageId = "com.mrfatworm.zzzarchive"

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 35

    defaultConfig {
        applicationId = zzzPackageId
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = zzzVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            ndk.debugSymbolLevel = "FULL"
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("variant")
    productFlavors {
        create("Dev") {
            dimension = "variant"
            isDefault = true
            applicationIdSuffix = ".dev"
            versionNameSuffix = " Beta"
        }
        create("Live") {
            dimension = "variant"
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        var desktopPackageName = ""
        var desktopPackageId = ""
        if ((System.getenv("VARIANT") ?: "") == "Live") {
            desktopPackageName = "ZZZ Archive"
            desktopPackageId = zzzPackageId
        } else {
            desktopPackageName = "ZZZ Archive Dev"
            desktopPackageId = "$zzzPackageId.dev"
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = desktopPackageName
            packageVersion = windowsVersionName
            linux {
                iconFile.set(project.file("desktopLogo/Logo.png"))
            }
            windows {
                iconFile.set(project.file("desktopLogo/Logo.ico"))
            }
            macOS {
                iconFile.set(project.file("desktopLogo/Logo.icns"))
                bundleID = desktopPackageId
            }
        }
    }
}

// Ref: https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/
project.extra.set("buildkonfig.flavor", currentBuildVariant())
buildkonfig {
    packageName = zzzPackageId
    objectName = "ZzzConfig"
    exposeObjectWithName = "ZzzConfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "variant", "Beta")
        buildConfigField(FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset")
        buildConfigField(FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api")
        buildConfigField(FieldSpec.Type.STRING, "VERSION", "$zzzVersionName-Beta")
    }

    defaultConfigs("Dev") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "Beta")
        buildConfigField(FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset")
        buildConfigField(FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api")
        buildConfigField(FieldSpec.Type.STRING, "VERSION", "$zzzVersionName-Beta")
    }

    defaultConfigs("Live") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "Stable")
        buildConfigField(FieldSpec.Type.STRING, "ASSET_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Asset")
        buildConfigField(FieldSpec.Type.STRING, "API_PATH", "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Api")
        buildConfigField(FieldSpec.Type.STRING, "VERSION", zzzVersionName)
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
    val variant = if (matcher.find()) matcher.group(1).lowercase() else null
    return if (variant in variants) {
        variant
    } else {
        null
    }
}

private fun Project.currentBuildVariant(): String {
    val variants = setOf("Dev", "Live")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: "Dev"
}
