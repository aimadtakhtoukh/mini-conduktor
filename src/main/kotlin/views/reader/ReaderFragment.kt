package views.reader

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import tornadofx.*
import views.topics.TopicView

class ReaderFragment: Fragment() {
    private val controller: ReaderController by inject()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.JavaFx) {
                controller.listenToKafka()
            }
        }
    }

    override val root = borderpane {
        title = "Listening to ${controller.selectedTopic()}"
        center = listview(controller.observableList)
        bottom = button("Stop listening") {
            action {
                controller.stopListening()
                replaceWith<TopicView>(sizeToScene = true)
            }
        }
        setPrefSize(800.0, 600.0)
    }
}