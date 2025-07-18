@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven("https://api.xposed.info/")
    }
}

plugins {
    id("com.highcapable.sweetproperty") version "1.0.5"
}

rootProject.name = "AviumTool"
include(":app")
 