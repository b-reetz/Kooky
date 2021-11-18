import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        hilt()
        enro()
        sqlDelight()

        implementation(project(":data"))
        implementation(project(":navigation"))
    }
}