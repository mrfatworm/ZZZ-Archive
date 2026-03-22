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

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 36

    defaultConfig {
        applicationId = libs.versions.zzzPackageId.get()
        minSdk = 26
        targetSdk = 36
        versionCode = libs.versions.zzzVersionCode.get().toInt()
        versionName = libs.versions.zzzVersionName.get()
    }

    flavorDimensions.add("variant")
    productFlavors {
        create("Dev") {
            isDefault = true
            dimension = "variant"
            applicationIdSuffix = ".dev"
            versionNameSuffix = " Beta"
            resValue("string", "app_name_variant", "ZZZ Archive-Beta")
        }
        create("Live") {
            dimension = "variant"
            resValue("string", "app_name_variant", "ZZZ Archive")
        }
    }

    buildFeatures {
        resValues = true
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
