plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.sample.knowmerce"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sample.knowmerce"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21

        // Desugaring 활성화
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }

    flavorDimensions += "environment"
    productFlavors {
        create("sample") {
            dimension = "environment"
        }
    }
}

dependencies {
    implementation(project(":feature:main"))

    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // desugar
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.androidx.multidex)

    // Room
    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.compiler)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose)

    // Coil
    implementation(platform(libs.coil.bom))
    implementation(libs.bundles.coil)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.okhttp3)
    implementation(libs.bundles.retrofit)

    // Gson
    implementation(libs.gson)
}