plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.geeks.rickandmortyy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.geeks.rickandmortyy"
        minSdk = 26
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
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //ExpandableLayout
    implementation ("com.github.skydoves:expandablelayout:1.0.7")

    //Coil
    implementation ("io.coil-kt:coil:2.4.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    //paging3
    val paging_version = "3.3.0"
    implementation("androidx.paging:paging-runtime:$paging_version")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //viewModel
    val lifecycle_version = "2.8.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")

    //Koin
    implementation("io.insert-koin:koin-android:3.5.6")
    //implementation ("org.koin:koin-androidx-viewmodel:3.1.2")
}