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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    implementation("com.android.tools.build:gradle:7.0.3")
}