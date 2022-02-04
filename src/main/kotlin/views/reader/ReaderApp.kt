package views.reader

import kafka.ConsumerApi
import tornadofx.App
import tornadofx.Controller
import tornadofx.asObservable

class ReaderApp : App(ReaderView::class)

class ReaderController: Controller() {
    val observableList = mutableListOf<String>().asObservable()

    suspend fun listenToKafka() {
        ConsumerApi.readTopics(listOf("topic")).collect { observableList.add(it.value())}
    }
}