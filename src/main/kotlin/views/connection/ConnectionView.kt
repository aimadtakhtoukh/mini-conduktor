package views.connection

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextField
import tornadofx.*
import views.topics.TopicView

class ConnectionView : View() {
    private var kafkaUrlTextField: TextField by singleAssign()
    private val controller: ConnectionController by inject()
    private val kafkaUrlProperty = SimpleStringProperty()

    init {
        title = "Connect to Kafka"
    }

    override val root =
        borderpane {
            left = hbox {
                kafkaUrlTextField = textfield(kafkaUrlProperty) {
                    text = controller.defaultKafka
                    promptText = controller.defaultKafka
                }
            }
            right = button("Connect") {
                action {
                    controller.updateKafkaUrl(kafkaUrlProperty.get())
                    replaceWith<TopicView>(sizeToScene = true)
                }
                requestFocus()
            }
            setPrefSize(300.0, 30.0)
        }
}