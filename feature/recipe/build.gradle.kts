import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        hilt()
        enro()
        implementation(project(":data"))
        implementation(project(":navigation"))
    }
}