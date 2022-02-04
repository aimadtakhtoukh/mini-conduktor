package views.connection

import tornadofx.App
import tornadofx.Controller
import views.appstate.ConsumerPropertiesState

class ConnectionApp : App(ConnectionView::class)

class ConnectionController : Controller() {
    val defaultKafka = ConsumerPropertiesState.localKafka

    fun updateKafkaUrl(newUrl : String?) {
        ConsumerPropertiesState.setServers(newUrl)
    }
}