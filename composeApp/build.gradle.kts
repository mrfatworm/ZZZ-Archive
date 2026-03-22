import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
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

val zzzVersionName = "1.7.0"
val zzzVersionCode = 12
val zzzPackageId = "com.mrfatworm.zzzarchive"

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
    return if (variant in variants) variant else null
}

fun Project.currentBuildVariant(): String {
    val variants = setOf("Dev", "Live")
    return getAndroidBuildVariantOrNull()
        ?: System.getenv("VARIANT")?.takeIf { it in variants }
        ?: "Dev"
}

val localProperties = project.rootProject.file("local.properties")
val aesKey: String =
    Properties().apply { if (localProperties.exists()) load(localProperties.inputStream()) }
        .getProperty("AES_KEY")
        ?: "eryuQ00pQZ16die2sfaPerkoGwQVM9jXACLNAMPHM/M=" // Fake key for open-source

buildConfig {
    packageName = zzzPackageId
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

