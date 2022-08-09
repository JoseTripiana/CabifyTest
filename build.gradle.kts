buildscript {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://maven.fabric.io/public") }
    }

    dependencies {
        classpath(BuildConstants.Classpath.androidGradlePlugin)
        classpath(BuildConstants.Classpath.kotlinGradlePlugin)
    }

    allprojects {
        repositories {
            mavenCentral()
            google()
            maven { url = uri("https://plugins.gradle.org/m2/") }
            maven { url = uri("https://maven.fabric.io/public") }
        }
    }

    tasks.register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}


