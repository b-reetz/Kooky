import com.kooky.buildextensions.implementation

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.squareup.sqldelight")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kooky"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin-RequiresOptIn"
        jvmTarget = "1.8"
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Versions.compose }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/DEPENDENCIES")
        }
    }
}

dependencies {
    compose()
    hilt()
    enro()
    sqlDelight()
    timber()

    implementation(project(":feature:recipe"))
    implementation(project(":feature:add"))
    implementation(project(":feature:shoppinglist"))
    implementation(project(":data"))
    implementation(project(":navigation"))
    implementation(project(":utilities"))

    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.activity:activity-compose:${Versions.composeActivity}")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
}