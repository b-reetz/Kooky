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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation("com.android.tools.build:gradle:7.2.2")
    implementation("com.squareup:javapoet:1.13.0")
}