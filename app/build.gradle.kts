import org.gradle.api.GradleException

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

val keystoreFilePath = System.getenv("KEYSTORE_PATH")
val keystorePassword = System.getenv("STORE_PASSWORD")
val keyAlias = System.getenv("KEY_ALIAS")
val keyPassword = System.getenv("KEY_PASSWORD")
val releaseKeystoreFile = keystoreFilePath
    ?.takeIf { it.isNotBlank() }
    ?.let { file(it) }
    ?.takeIf { it.exists() }
val hasCustomReleaseSigning = releaseKeystoreFile != null &&
    !keystorePassword.isNullOrBlank() &&
    !keyAlias.isNullOrBlank() &&
    !keyPassword.isNullOrBlank()

android {
    namespace = "com.picavox.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.picavox.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        if (hasCustomReleaseSigning) {
            create("release") {
                storeFile = releaseKeystoreFile
                storePassword = keystorePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.findByName("release")
                ?: signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.matching { it.name in setOf("bundleRelease", "assembleRelease") }.configureEach {
    doFirst {
        val keystoreFile = System.getenv("KEYSTORE_PATH")
            ?.takeIf { it.isNotBlank() }
            ?.let { project.file(it) }
            ?.takeIf { it.exists() }
        val hasCustomReleaseSigning = keystoreFile != null &&
            !System.getenv("STORE_PASSWORD").isNullOrBlank() &&
            !System.getenv("KEY_ALIAS").isNullOrBlank() &&
            !System.getenv("KEY_PASSWORD").isNullOrBlank()

        if (!hasCustomReleaseSigning) {
            throw GradleException(
                "Release builds require KEYSTORE_PATH, STORE_PASSWORD, KEY_ALIAS, and KEY_PASSWORD."
            )
        }
    }
}
