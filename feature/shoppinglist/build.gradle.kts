import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        hilt()
        enro()
//        sqlDelight()
//        kotlinxSerialization()

        implementation(project(":data"))
        implementation(project(":navigation"))
        implementation(project(":utilities"))
//        implementation(project(":infrastructure:viewmodel"))
//        implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    }
}