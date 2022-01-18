import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        hilt()
        enro()
        sqlDelight()
        retrofit()
        okHttp()
        kotlinxSerialization()

        implementation(project(":data"))
        implementation(project(":navigation"))
        implementation(project(":utilities"))
        implementation(project(":infrastructure:viewmodel"))
        implementation("it.skrape:skrapeit:1.1.6")
        implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    }
}