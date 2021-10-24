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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("com.android.tools.build:gradle:7.1.0-alpha08")
}