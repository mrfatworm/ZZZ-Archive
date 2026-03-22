import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.buildConfig)
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

        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
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
            implementation(libs.kotlinx.coroutines.test)
        }

        val androidHostTest by getting
        androidHostTest.dependencies {
            implementation(libs.mockk)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.compose.uiToolingPreview)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.cryptography.provider.apple)
        }

        val desktopMain by getting

        desktopMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        room {
            schemaDirectory("$projectDir/schemas")
        }
    }
}

compose.resources {
    publicResClass = true
}

dependencies {
    ksp(libs.androidx.room.compiler)
    androidRuntimeClasspath(libs.compose.uiTooling)
}


kotlinter {
    ignoreFormatFailures = false
    ignoreLintFailures = false
    reporters = arrayOf("checkstyle", "plain")
}

fun Project.getAndroidBuildVariantOrNull(): String? {
    val variants = setOf("Dev", "Live")
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val regex = if ("assemble" in taskRequestsStr) {
        Regex("assemble(\\w+)(Release|Debug)")
    } else {
        Regex("bundle(\\w+)(Release|Debug)")
    }
    val variant = regex.find(taskRequestsStr)?.groupValues?.get(1)
    return variant?.takeIf { it in variants }
}

fun Project.currentBuildVariant(): String {
    val variants = setOf("Dev", "Live")
    return getAndroidBuildVariantOrNull()
        ?: providers.environmentVariable("VARIANT").orNull?.takeIf { it in variants }
        ?: "Dev"
}

val localPropertiesFile = project.rootProject.file("local.properties")
val aesKey: String = if (localPropertiesFile.exists()) {
    localPropertiesFile.readLines()
        .firstOrNull { it.startsWith("AES_KEY=") }
        ?.substringAfter("=")
        ?: "eryuQ00pQZ16die2sfaPerkoGwQVM9jXACLNAMPHM/M="
} else {
    "eryuQ00pQZ16die2sfaPerkoGwQVM9jXACLNAMPHM/M=" // Fake key for open-source
}

val zzzVersionName = libs.versions.zzzVersionName.get()

buildConfig {
    packageName = libs.versions.zzzPackageId.get()
    className = "ZzzConfig"

    val variant = currentBuildVariant()
    val isLive = variant == "Live"

    buildConfigField<String>(
        "ASSET_PATH",
        if (isLive) "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Asset"
        else "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset"
    )
    buildConfigField<String>(
        "API_PATH",
        if (isLive) "mrfatworm/ZZZ-Archive-Asset/refs/heads/main/Api"
        else "mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Api"
    )
    buildConfigField<String>(
        "VERSION",
        if (isLive) zzzVersionName else "$zzzVersionName-Beta"
    )
    buildConfigField<String>("AES_KEY", aesKey)
}

