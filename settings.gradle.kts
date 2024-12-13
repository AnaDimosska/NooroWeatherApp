pluginManagement {
    repositories {
        google() // Android and Jetpack Compose dependencies
        mavenCentral() // Additional libraries
        gradlePluginPortal() // Gradle plugin dependencies
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Android and Jetpack Compose dependencies
        mavenCentral() // Additional libraries
    }
}

rootProject.name = "NooroWeatherApp"
include(":app")
