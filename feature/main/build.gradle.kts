plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.sample.knowmerce.feature.main"
    compileSdk = 35

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose = true
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // desugar
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Paging3
    implementation(libs.bundles.androidx.pageing3)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose)

    // Coil
    implementation(platform(libs.coil.bom))
    implementation(libs.bundles.coil)

    // Gson
    implementation(libs.gson)
}