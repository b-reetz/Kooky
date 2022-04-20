import com.android.build.gradle.LibraryExtension
import com.kooky.buildextensions.implementation
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("unused")
open class KookyBuildExtension(private val project: Project) {
    var useCompose = false
    var useSqlDelight = false

    fun KookyBuildExtension.dependencies(configuration: DependencyHandlerScope.() -> Unit) {
        //Needs the plugins applied first before we can use configuration names like 'implementation'
        project.afterEvaluate {
            dependencies(configuration)
        }
    }

    fun KookyBuildExtension.plugins(configuration: PluginContainer.() -> Unit) {
        project.afterEvaluate {
            configuration.invoke(plugins)
        }
    }
}

private const val extensionName = "kooky-internal"

fun Project.kooky(configure: Action<KookyBuildExtension> = Action { }) {
    val extension = extensions.getByType(KookyBuildExtension::class)
    configure.execute(extension)

    plugins.apply {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
        apply("kotlin-android")
        apply("kotlin-parcelize")
        apply("kotlin-kapt")
        apply("kotlinx-serialization")

        if (extension.useSqlDelight) apply("com.squareup.sqldelight")
    }

    configure<LibraryExtension> {
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

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                excludes.add("/META-INF/DEPENDENCIES")
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions { jvmTarget = "1.8" }
    }

    dependencies {
        if (extension.useCompose) compose()
        if (extension.useSqlDelight) sqlDelight()

        timber()

        implementation("androidx.core:core-ktx:${Versions.coreKtx}")
        implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    }
}

class KookyBuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create<KookyBuildExtension>(extensionName, project)
    }
}