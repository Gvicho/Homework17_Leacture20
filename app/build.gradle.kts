plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin") // for navigation

    //Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.homework17_leacture20"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.homework17_leacture20"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        viewBinding = true
    }
}

//Hilt
// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    //Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit

    implementation ("com.squareup.moshi:moshi:1.9.3") // Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.9.3") // For Kotlin support

    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")// Retrofit Converter for Moshi



    implementation("com.github.bumptech.glide:glide:4.16.0") // for glide



    implementation("com.google.android.material:material:1.12.0-alpha02") // For circular images, and others


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // coroutines


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") // For ViewModel
    implementation ("androidx.activity:activity-ktx:1.8.2") // For ViewModel

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6") // for navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")// for navigation

    implementation("androidx.fragment:fragment-ktx:1.6.2")//for fragment
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}