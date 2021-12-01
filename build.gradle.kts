buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.1")
    }
}

subprojects {
    if (projectDir.path != "$rootDir/app")
        plugins.apply(KookyBuildPlugin::class)
}