package views.appstate

object SelectedTopicState {
    private lateinit var topic : String
    private var listening : Boolean = true

    fun selectedTopic() = topic

    fun setSelectedTopic(t: String) {topic = t}

    fun keepListening() = listening

    fun stopListening() { listening = false }

    fun startListening() { listening = true }
}