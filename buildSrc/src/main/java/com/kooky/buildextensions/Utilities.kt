package com.kooky.buildextensions

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.add

fun DependencyHandler.implementation(dependency: String, configuration: ExternalModuleDependency.() -> Unit = {}) {
    add("implementation", dependency, configuration)
}

fun DependencyHandler.implementation(dependency: ProjectDependency, configuration: ProjectDependency.() -> Unit = {}) {
    add("implementation", dependency, configuration)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

fun DependencyHandler.compileOnly(dependency: String) {
    add("compileOnly", dependency)
}

fun DependencyHandler.api(dependency: String) {
    add("api", dependency)
}