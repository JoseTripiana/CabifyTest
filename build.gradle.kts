buildscript {
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath(BuildConstants.Classpath.androidGradlePlugin)
        classpath(BuildConstants.Classpath.kotlinGradlePlugin)
    }

    allprojects {

    }

    tasks.register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}


