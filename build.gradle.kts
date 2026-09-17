plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidBuiltInKotlin) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.buildConfig) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
}

// Resolved once here so the Android flavor, desktop packaging and ZzzConfig can never disagree
// about which asset branch a build targets. Every module reads it back from its own `extra`.
val zzzVariant = providers.gradleProperty("zzz.variant").orNull ?: "Dev"
require(zzzVariant in setOf("Dev", "Live")) {
    "zzz.variant must be \"Dev\" or \"Live\" but was \"$zzzVariant\". " +
        "Set it in gradle.properties or pass -Pzzz.variant=Live."
}

subprojects {
    extra["zzzVariant"] = zzzVariant
}
