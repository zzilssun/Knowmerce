plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sample.knowmerce.core.ui"
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
}