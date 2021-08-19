package geekbrains.slava_5655380.domain.models.conversions_history

class ConversionsHistoryRepo {
    private val history: ArrayList<ConversionMetadata> = arrayListOf()
    fun add(metadata: ConversionMetadata){
        history.add(metadata)
    }
}