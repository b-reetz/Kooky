import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        enro()
        implementation(project(":data"))
        implementation(project(":navigation"))
    }
}