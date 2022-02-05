package kafka

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.yield
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import views.appstate.ConsumerPropertiesState
import views.appstate.SelectedTopicState
import java.time.Duration

object ConsumerApi {
    private fun consumer(): KafkaConsumer<String, String> =
        KafkaConsumer<String, String>(ConsumerPropertiesState.consumerProps())

    fun listTopics() : List<String> =
        consumer().listTopics().keys.toList()

    fun readTopics(topics : List<String>) : Flow<ConsumerRecord<String, String>> {
        val consumer = consumer().apply { subscribe(topics) }
        return flow {
            while (SelectedTopicState.keepListening()) {
                consumer
                    .poll(Duration.ofMillis(50))
                    .forEach { record -> emit(record) }
                yield()
            }
        }
    }
}