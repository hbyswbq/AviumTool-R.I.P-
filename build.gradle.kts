buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

