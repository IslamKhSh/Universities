import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.detekt)

    // just apply in the module level with id only
    alias(libs.plugins.androidApp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.safeArgs) apply false
}

tasks.withType<Detekt> { jvmTarget = "1.8" }

// run detekt over all modules and get one report for all modules
val detektAll by tasks.registering(Detekt::class) {

    description = "Runs over the entire code base without the starting overhead for each module and performs check."

    parallel = true
    buildUponDefaultConfig = true
    autoCorrect = true

    setSource(files(rootDir))
    config.setFrom(files("$rootDir/detekt-config.yml"))

    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**")

    reports {
        md.required.set(false)
        txt.required.set(true)
        html.required.set(true)
    }
}