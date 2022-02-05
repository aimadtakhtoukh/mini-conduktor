package views.topics

import tornadofx.*
import views.reader.ReaderFragment

class TopicView : View() {
    private val controller : TopicController by inject()
    private val topicList = controller.topicList.asObservable()

    override val root = vbox {
        title = "Topic List"
        listview(topicList) {
            bindSelected(controller.selectedTopic)
            onDoubleClick {
                controller.startListeningToSelectedTopic()
                setPrefSize(300.0, 600.0)
                replaceWith<ReaderFragment>(sizeToScene = true)
            }
        }
    }
}
