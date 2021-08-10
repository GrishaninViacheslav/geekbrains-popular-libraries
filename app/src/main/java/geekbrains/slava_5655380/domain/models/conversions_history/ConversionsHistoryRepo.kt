package geekbrains.slava_5655380.domain.models.conversions_history

import io.reactivex.Single

class ConversionsHistoryRepo {
    private val conversionsHistory = listOf(
        ConversionMetadata("img1"),
        ConversionMetadata("img2"),
        ConversionMetadata("img3"),
        ConversionMetadata("img4"),
        ConversionMetadata("img5")
    )

    fun getHistory(): Single<List<ConversionMetadata>> = Single.just(conversionsHistory)

    fun getConversionMetadata(id: String): Single<ConversionMetadata> =
        Single.just(conversionsHistory.first { user -> user.name == id })
}