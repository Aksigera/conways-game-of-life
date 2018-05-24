import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.HBox
import tornadofx.*

class Field : View() {
    override val root: HBox by fxml()

    init {
        title = "Conway's Game of Life"

        root.lookupAll(".button").forEach { b ->
            b.setOnMouseClicked {
                operator((b as Button).text)
            }
        }

        root.addEventFilter(KeyEvent.KEY_TYPED) {
            operator(if (it.code == KeyCode.ENTER) "=" else it.character)
        }
    }
}