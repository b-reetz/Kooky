import com.kooky.buildextensions.implementation

kooky {
    useCompose = true

    dependencies {
        implementation(project(":data"))
    }
}