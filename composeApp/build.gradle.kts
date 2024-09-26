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
    jvmToolchain(11)
    androidTarget {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-test.html
        @OptIn(ExperimentalKotlinGradlePluginApi::class) instrumentedTestVariant.sourceSetTree.set(
            KotlinSourceSetTree.test
        )
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
            implementation(libs.coil)
            implementation(libs.coil.network.ktor)
            implementation(libs.coil.mp)
            implementation(libs.coil.compose)
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.bundles.ktor)
            implementation(libs.okio)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.assertk)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
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

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mrfatworm.zzzarchive"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
    }

    flavorDimensions.add("variant")
    productFlavors {
        create("dev") {
            dimension = "variant"
            isDefault = true
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "ASSET_PATH", "\"mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset\"")
            buildConfigField("String", "API_PATH", "\"/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api/\"")
        }
        create("prod") {
            dimension = "variant"
            buildConfigField("String", "ASSET_PATH", "\"/mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Asset/\"")
            buildConfigField("String", "API_PATH", "\"/mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Api/\"")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.mrfatworm.zzzarchive"
            packageVersion = "1.0.0"
        }
    }
}

// Ref: https://sujanpoudel.me/blogs/managing-configurations-for-different-environments-in-kmp/
project.extra.set("buildkonfig.flavor", currentBuildVariant())

buildkonfig {
    packageName = "com.mrfatworm.zzzarchive"
    objectName = "ZzzArchive"
    exposeObjectWithName = "ZzzArchive"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "variant", "dev")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://dev.example.com")
    }

    defaultConfigs("dev") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "dev")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://dev.example.com")
    }

    defaultConfigs("prod") {
        buildConfigField(FieldSpec.Type.STRING, "variant", "prod")
        buildConfigField(FieldSpec.Type.STRING, "apiEndPoint", "https://example.com")
    }
}

fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("dev", "prod")
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
    val variants = setOf("dev", "prod")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: "dev"
}
