@file:JvmName("Dependencies")

import com.kooky.buildextensions.implementation
import com.kooky.buildextensions.kapt
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandler.kotlinxSerializationConverter() {
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinxSerializationConverter}")
}

fun DependencyHandler.kotlinxSerialization() {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")
}

fun DependencyHandler.materialDesign() {
    implementation("com.google.android.material:material:${Versions.materialDesign}")
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
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha07")
//    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")
}

fun DependencyHandlerScope.sqlDelight() {
    implementation("com.squareup.sqldelight:android-driver:${Versions.sqlDelight}")
    implementation("com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}")
}

fun DependencyHandlerScope.enro() {
    implementation("dev.enro:enro:${Versions.enro}")
    kapt("dev.enro:enro-processor:${Versions.enro}")
}

fun DependencyHandlerScope.navigation() {
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha01")
}