@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.kotlinParcelize.get().pluginId)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.xische.universities.feature.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures { dataBinding = true }
}

dependencies {

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    api(libs.bundles.navigationComponent)
    api(libs.bundles.lifecycle)
    api(libs.bundles.androidKtx)
    api(libs.bundles.basicUi)
}
