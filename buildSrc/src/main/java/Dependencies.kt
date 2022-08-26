@file:JvmName("Dependencies")

import com.kooky.buildextensions.implementation
import com.kooky.buildextensions.kapt
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandler.kotlinxSerialization() {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")
}

fun DependencyHandler.coroutines() {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}")
}


fun DependencyHandler.okHttp() {
    implementation("com.squareup.okhttp3:okhttp:${Versions.okHttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}")
}

fun DependencyHandler.retrofit() {
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-jackson:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-scalars:${Versions.retrofit}")
}

//Requires the following plugins to be set
//  id("dagger.hilt.android.plugin")
//  id("kotlin-kapt")
fun DependencyHandlerScope.hilt() {
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
}

fun DependencyHandler.compose() {
    implementation("androidx.compose.runtime:runtime:${Versions.compose}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.compose.foundation:foundation:${Versions.compose}")
    implementation("androidx.compose.animation:animation:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.activity:activity-compose:${Versions.composeActivity}")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}")
}

fun DependencyHandlerScope.sqlDelight() {
    implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
    implementation("com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}")
}

fun DependencyHandlerScope.enro() {
    implementation("dev.enro:enro:${Versions.enro}")
    kapt("dev.enro:enro-processor:${Versions.enro}")
}

fun DependencyHandlerScope.timber() {
    implementation("com.jakewharton.timber:timber:${Versions.timber}")
}