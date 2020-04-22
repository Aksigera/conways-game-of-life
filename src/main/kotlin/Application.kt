import javafx.stage.Stage
import tornadofx.*
import kotlin.reflect.KClass

class Application : App() {
    val foo: Int = 1
    override val primaryView: KClass<out UIComponent> = Field::class

    override fun start(stage: Stage) {
        importStylesheet("/style.css")
        stage.isResizable = false
        super.start(stage)
    }
}