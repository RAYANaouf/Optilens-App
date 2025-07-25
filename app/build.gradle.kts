plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)


    alias( libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)

}

android {
    namespace = "com.example.optilens"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.optilens"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    //splash screen
    implementation(libs.androidx.core.splashscreen)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //compose animation
    // https://mvnrepository.com/artifact/androidx.compose.animation/animation
    implementation(libs.androidx.animation)



    //view model
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //dataStore preferences
    implementation(libs.androidx.datastore.preferences)


    //constraint layout
    // https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout-compose
    implementation(libs.androidx.constraintlayout.compose)


    /********************pager******************/
    // https://mvnrepository.com/artifact/androidx.paging/paging-compose
    implementation(libs.androidx.paging.compose)
    // https://mvnrepository.com/artifact/androidx.paging/paging-runtime-ktx
    implementation(libs.androidx.paging.runtime.ktx)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)

    /***************** room *********************/
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)



    /***************koin*************************/
    //koin-android
    implementation(libs.koin.android)
    //koin-androidx-navigation
    implementation(libs.koin.androidx.navigation)
    //koin-androidx-compose
    implementation(libs.koin.androidx.compose)

    //coil
    // https://mvnrepository.com/artifact/io.coil-kt/coil-compose
    implementation(libs.coil.compose)

    implementation(libs.coil.svg)     // for SVG support

    // ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    //kotlinx serialisation
    implementation(libs.ktor.serialization.kotlinx.json)
    //top bottom system bar
    implementation(libs.accompanist.systemuicontroller)

    //color picker
    implementation(libs.colorpicker.compose)



    //charts (kmp)
    // uses-sdk:minSdkVersion 24 cannot be smaller than version 26 declared in library [co.yml:ycharts:2.1.0
    //Suggestion: use a compatible library with a minSdk of at most 24,
    //Suggestion: use a compatible library with a minSdk of at most 24,
    // https://mvnrepository.com/artifact/co.yml/ychat
    //implementation(libs.ycharts)



    //lottie
    // https://mvnrepository.com/artifact/com.airbnb.android/lottie-compose
    implementation(libs.lottie.compose)


}