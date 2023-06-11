package ua.dimkas71.data

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ua.dimkas71.data.source.local.LocalSummaryConsumingBySectors
import ua.dimkas71.usecase.DateConverters
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class SummaryConsumingBySectors(
    @SerialName("period")
    val period: String,
    @SerialName("sectorNumber")
    val sector: Int,
    @SerialName("consuming")
    val consumed: Double
    )

fun SummaryConsumingBySectors.asLocalSummaryBySectors(): LocalSummaryConsumingBySectors {
    return LocalSummaryConsumingBySectors(
        DateConverters.asLong(this.period),
        this.sector,
        this.consumed
    )
}