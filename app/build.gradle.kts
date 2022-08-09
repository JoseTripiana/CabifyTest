plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = BuildConstants.MainSettings.compileSdk
    defaultConfig {
        versionName = BuildConstants.MainSettings.versionName
        versionCode = BuildConstants.MainSettings.versionCode
        applicationId = BuildConstants.MainSettings.applicationId
        minSdk = BuildConstants.MainSettings.minSdk
        targetSdk = BuildConstants.MainSettings.targetSdkVersion

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    signingConfigs {
        create("release"){
            storePassword = "G289qJ5gj7k3UugteDav"
            keyPassword = "G289qJ5gj7k3UugteDav"
            storeFile = file("../keystore/release.keystore")
            keyAlias = "release"
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isJniDebuggable = true
            isRenderscriptDebuggable = true
            manifestPlaceholders["crashlyticsEnabled"] = true
            isShrinkResources = true

            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    BuildConstants.appDependencies.forEach {
        implementation(it)
    }
    BuildConstants.testDependencies.forEach {
        testImplementation(it)
    }
    BuildConstants.androidTestDependencies.forEach {
        androidTestImplementation(it)
    }
}
