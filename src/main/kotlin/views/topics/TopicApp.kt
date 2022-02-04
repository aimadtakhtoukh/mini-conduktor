package views.topics

import kafka.ConsumerApi
import tornadofx.App
import tornadofx.Controller

class TopicApp : App(TopicView::class)

class TopicController : Controller() {
    val topicList = ConsumerApi.listTopics()

}