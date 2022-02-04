package views.reader

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import tornadofx.*

class ReaderView: View() {
    private val controller: ReaderController by inject()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.JavaFx) {
                controller.listenToKafka()
            }
        }
    }

    override val root = borderpane {
        title = "Listening to topic"
        center = listview(controller.observableList)
        setPrefSize(800.0, 600.0)
    }
}