plugins {
    `kotlin-dsl`
    `maven-publish`
    groovy
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.1.1")
}