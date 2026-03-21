import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "com.mrfatworm.zzzarchive")
        }
    }

    jvm("desktop")

    androidLibrary {
        namespace = "com.mrfatworm.zzzarchive.composeApp"
        compileSdk = 36
        minSdk = 26

        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
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
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.cryptography.provider.apple)
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }
    }
}

dependencies {
    ksp(libs.androidx.room.compiler)
    androidRuntimeClasspath(libs.compose.uiTooling)
}

val zzzVersionName = "1.7.0"
val zzzVersionCode = 12
val zzzPackageId = "com.mrfatworm.zzzarchive"

