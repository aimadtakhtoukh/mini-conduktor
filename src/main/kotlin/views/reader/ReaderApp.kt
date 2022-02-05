package views.reader

import kafka.ConsumerApi
import tornadofx.Controller
import tornadofx.asObservable
import views.appstate.SelectedTopicState

class ReaderController: Controller() {
    val observableList = mutableListOf<String>().asObservable()

    suspend fun listenToKafka() {
        ConsumerApi
            .readTopics(listOf(SelectedTopicState.selectedTopic()))
            .collect { observableList.add(it.value())}
    }

    fun stopListening() {
        SelectedTopicState.stopListening()
        observableList.clear()
    }

    fun selectedTopic(): String = SelectedTopicState.selectedTopic()
}