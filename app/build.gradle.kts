plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.rickandmorty"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.rickandmorty"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)


    // Compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha04")
    implementation ("androidx.navigation:navigation-compose:2.6.0-alpha04")
    implementation ("androidx.compose.material:material-icons-extended:1.3.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    //navigation animation
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.28.0")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")

    //coil
    implementation ("io.coil-kt:coil-compose:2.2.2")

    //lottie
    implementation ("com.airbnb.android:lottie-compose:5.2.0")

    //splash api
    implementation ("androidx.core:core-splashscreen:1.0.0")

    //pagination
    //paging
    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
}