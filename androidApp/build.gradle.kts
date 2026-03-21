plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

dependencies {
    implementation(projects.composeApp)

    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.cryptography.provider.jdk)
    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)
    testImplementation(libs.mockk)
    room {
        schemaDirectory("$projectDir/schemas")
    }

    ksp(libs.androidx.room.compiler)
}

val zzzVersionName = "1.7.0"
val zzzVersionCode = 12
val zzzPackageId = "com.mrfatworm.zzzarchive"

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 36

    defaultConfig {
        applicationId = zzzPackageId
        minSdk = 26
        targetSdk = 36
        versionCode = zzzVersionCode
        versionName = zzzVersionName
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}
