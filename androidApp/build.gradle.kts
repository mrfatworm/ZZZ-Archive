plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kotlin.serialization)
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
}

val zzzVariant = project.extra["zzzVariant"] as String

android {
    namespace = "com.mrfatworm.zzzarchive"
    compileSdk = 37

    defaultConfig {
        applicationId = libs.versions.zzzPackageId.get()
        minSdk = 26
        targetSdk = 36
        versionCode = libs.versions.zzzVersionCode.get().toInt()
        versionName = libs.versions.zzzVersionName.get()
    }

    // Only the flavor named by zzz.variant is created, so `assembleLiveRelease` exists only under
    // -Pzzz.variant=Live. A mismatch fails as an unknown task instead of quietly shipping the wrong
    // asset branch.
    flavorDimensions.add("variant")
    productFlavors {
        create(zzzVariant) {
            isDefault = true
            dimension = "variant"
            if (zzzVariant == "Dev") {
                applicationIdSuffix = ".dev"
                versionNameSuffix = " Beta"
                resValue("string", "app_name_variant", "ZZZ Archive-Beta")
            } else {
                resValue("string", "app_name_variant", "ZZZ Archive")
            }
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
