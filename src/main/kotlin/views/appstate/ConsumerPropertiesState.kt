package views.appstate

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

object ConsumerPropertiesState {
    const val localKafka = "localhost:9092"

    private fun defaultConsumerProps() : Map<String, String?> = mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to localKafka,
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.qualifiedName,
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.qualifiedName,
        "batch.size" to "1"
    )

    private fun fileConsumerProps() : Map<String, String?> {
        val properties = Properties()
        properties.load(this::class.java.getResourceAsStream("/consumer.properties")!!)
        return properties.stringPropertyNames().associateWith { properties.getProperty(it) }
    }

    private var userDefinedProps = mutableMapOf<String, String>()

    fun setServers(servers : String?) {
        userDefinedProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] =
            when(servers) {
                null, "" -> localKafka
                else -> servers
            }
    }

    fun consumerProps(): Map<String, String?> =
        listOf(userDefinedProps, fileConsumerProps(), defaultConsumerProps())
            .flatMap { it.asSequence() }
            .groupBy ({ it.key }, {it.value})
            .mapValues { it.value.first() }
}