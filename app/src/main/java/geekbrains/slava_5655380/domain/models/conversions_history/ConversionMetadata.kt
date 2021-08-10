package geekbrains.slava_5655380.domain.models.conversions_history

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConversionMetadata(
    val name: String
) : Parcelable