package views.topics;

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import views.reader.ReaderView

class TopicView : View() {
    private val controller : TopicController by inject()
    private val topicList = controller.topicList.asObservable()
    private val selectedTopic = SimpleStringProperty()

    override val root = vbox {
        title = "Topic List"
        listview(topicList) {
            bindSelected(selectedTopic)
            onDoubleClick {
                setPrefSize(300.0, 600.0)
                replaceWith<ReaderView>(sizeToScene = true)
            }
        }
    }
}
