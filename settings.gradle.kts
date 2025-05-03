pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PricePal"
include(":app")
include(":core")
include(":design")
include(":data")
include(":feature")
include(":feature:start")
include(":feature:home")
include(":feature:search")
include(":feature:taxi")
include(":test:start")
include(":test:home")
include(":test:search")
include(":test:taxi")
