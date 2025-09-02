plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "uz.polat.kitoblaruzb_eng"
    compileSdk = 35

    defaultConfig {
        applicationId = "uz.polat.kitoblaruzb_eng"
        minSdk = 24
        targetSdk = 35
        versionCode = 4
        versionName = "4.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            ndk.debugSymbolLevel = "FULL"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    androidResources{
        generateLocaleConfig = true
    }
    ndkVersion = "29.0.13113456 rc1"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //hilt
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.55")
    //voyager
    val voyagerVersion = "1.1.0-beta03"
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-hilt:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    //orbit
//    implementation("org.orbit-mvi:orbit-viewmodel:9.0.0")
//    implementation("org.orbit-mvi:orbit-compose:9.0.0")
    implementation("org.orbit-mvi:orbit-viewmodel:4.6.1")
    implementation("org.orbit-mvi:orbit-compose:4.6.1")

//
//    //retrofit
//    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
//    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    //coil
    implementation("io.coil-kt:coil-compose:2.7.0")


    //sites: https://www.pdfdrive.com/   https://manybooks.net/

    //room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //pdf viewer
    implementation("io.github.afreakyelf:Pdf-Viewer:2.1.1")
    implementation(kotlin("script-runtime"))

    //acompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

    //timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    //admob
    implementation("com.google.android.gms:play-services-ads:24.3.0")

}