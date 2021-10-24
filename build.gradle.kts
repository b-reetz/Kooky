buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

subprojects {
    if (projectDir.path != "$rootDir/app")
        plugins.apply(KookyBuildPlugin::class)
}