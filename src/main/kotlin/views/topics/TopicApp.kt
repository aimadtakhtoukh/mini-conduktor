package views.topics

import javafx.beans.property.SimpleStringProperty
import kafka.ConsumerApi
import tornadofx.App
import tornadofx.Controller
import views.appstate.SelectedTopicState

class TopicApp : App(TopicView::class)

class TopicController : Controller() {
    val selectedTopic = SimpleStringProperty()
    val topicList = ConsumerApi.listTopics()

    fun startListeningToSelectedTopic() {
        SelectedTopicState.setSelectedTopic(selectedTopic.get())
        SelectedTopicState.startListening()
    }
}