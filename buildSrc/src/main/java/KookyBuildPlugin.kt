import com.android.build.gradle.LibraryExtension
import com.kooky.buildextensions.implementation
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

open class KookyBuildExtension(private val project: Project) {
    var useCompose = false
}

private const val extensionName = "kooky-internal"

fun Project.kooky(configure: Action<KookyBuildExtension> = Action { }) {
    val extension = extensions.getByType(KookyBuildExtension::class)
    configure.execute(extension)

    project.apply(plugin = "com.android.library")
    project.apply(plugin = "org.jetbrains.kotlin.android")
    project.apply(plugin = "kotlin-android")
    project.apply(plugin = "kotlin-parcelize")
    project.apply(plugin = "kotlin-kapt")

    project.configure<LibraryExtension> {
        compileSdk = Versions.compileSdk
        defaultConfig {
            minSdk = Versions.minSdk
            targetSdk = Versions.targetSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures { compose = extension.useCompose }
        composeOptions { kotlinCompilerExtensionVersion = Versions.compose }
    }

    project.tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions { jvmTarget = "1.8" }
    }

    project.dependencies {
        if (extension.useCompose) compose()

        implementation("androidx.core:core-ktx:1.6.0")
        implementation("androidx.appcompat:appcompat:1.3.1")
        implementation("com.google.android.material:material:1.4.0")
    }
}

class KookyBuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create<KookyBuildExtension>(extensionName, project)
    }
}