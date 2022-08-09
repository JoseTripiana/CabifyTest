object BuildConstants {

    object MainSettings {
        const val kotlinVersion = "1.7.0"
        const val targetSdkVersion = 31
        const val compileSdk = 31
        const val minSdk = 25
        const val applicationId = "com.tripiana.cabifytest"
        const val versionName = "1.0.0"
        const val versionCode = 1
    }

    val appDependencies = mutableListOf<String>().apply {
        val appCompatVersion = "1.4.1"
        add("androidx.appcompat:appcompat:$appCompatVersion")

        val ktxCore = "1.7.0"
        add("androidx.core:core-ktx:$ktxCore")

        val materialVersion = "1.6.1"
        add("com.google.android.material:material:$materialVersion")

        val okHttpVersion = "4.10.0"
        add("com.squareup.okhttp3:okhttp:$okHttpVersion")
        add("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

        val coroutinesVersion = "1.6.1"
        add("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

        val lifecycleVersion = "2.4.1"
        add("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        add("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

        val gsonVersion = "2.9.0"
        add("com.google.code.gson:gson:$gsonVersion")

        val koinVersion = "3.2.0"
        add("io.insert-koin:koin-android:$koinVersion")

        val retrofitVersion = "2.9.0"
        add("com.squareup.retrofit2:retrofit:$retrofitVersion")
        add("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    }

    val testDependencies = mutableListOf<String>().apply {
        val jUnitVersion = "4.13.2"
        add("junit:junit:$jUnitVersion")
    }

    val androidTestDependencies = mutableListOf<String>().apply {
        val androidJUnitVersion = "1.1.3"
        add("androidx.test.ext:junit:$androidJUnitVersion")
    }


    object Classpath {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.1"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${MainSettings.kotlinVersion}"
    }
}