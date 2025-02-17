plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.detailstudy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.detailstudy"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation ("com.google.firebase:firebase-database:21.0.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1") // 최신 버전 확인 필요
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1") // Glide 컴파일러
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation (libs.volley)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.places)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}