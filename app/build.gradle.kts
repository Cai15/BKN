plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.bkn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bkn"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
        vectorDrawables {
            useSupportLibrary = true
        }

    }


    kapt {
        generateStubs = true
        correctErrorTypes = true
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
        viewBinding = true
        compose = true
        dataBinding = true
        prefab = true
    }
    composeOptions {
        // kotlinCompilerExtensionVersion = "1.6.3"
        //kotlinCompilerVersion = "1.5.3"
        kotlinCompilerExtensionVersion =  "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "34.0.0"
    ndkVersion = "26.2.11394342"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material3)
    implementation(libs.support.annotations)
    //implementation("androidx.annotation:annotation:1.7.1")
    annotationProcessor(libs.androidx.room.compiler.processing.testing)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    implementation(libs.androidx.cardview)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.activity.compose.v182)

    implementation(libs.androidx.ui.v170alpha01)
    implementation(libs.androidx.ui.graphics.v170alpha01)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.material3.v120rc01)
    implementation(libs.material.v1120alpha03)
    implementation (libs.androidx.viewpager2.v110beta02)
    implementation (libs.androidx.recyclerview)
    implementation (libs.recyclerview.animators)
    implementation (libs.androidx.coordinatorlayout)
    androidTestImplementation(platform(libs.androidx.compose.bom.v20240100))
    androidTestImplementation(libs.androidx.ui.test.junit4.v170alpha01)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation (libs.androidx.navigation.fragment.ktx.v276)
    implementation (libs.androidx.navigation.ui.ktx.v276)

    //Glide
    implementation (libs.glide)
    //annotationProcessor ("com.github.bump tech.glide:compiler:4.11.0")

    implementation (libs.lottie.v630)

    //okHttp
    implementation (libs.okhttp)

    //Retrofit
    implementation (libs.retrofit.v290)
    implementation (libs.converter.gson.v290)
    implementation (libs.logging.interceptor)

    //dagger
    kapt (libs.kotlinx.metadata.jvm)
    implementation (libs.dagger.v250)
    implementation (libs.dagger.android)
    implementation (libs.dagger.android.support)
    kapt (libs.dagger.compiler.v250)
    kapt (libs.dagger.android.processor)
}