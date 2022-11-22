package andgo.dunamuportfolio.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


interface UpbitRequestParam

data class SubscribeData(
    val params: List<UpbitRequestParam>
) {
    companion object {
        fun create(
            ticket: Ticket,
            type: Type
        ) = SubscribeData(listOf(type, ticket))
    }
}


@JsonClass(generateAdapter = true)
data class Ticket(
    @Json(name = "ticket") val ticket: String
) : UpbitRequestParam


data class Type(
    @Json(name = "type") val type: String,
    @Json(name = "codes") val codes: List<String>
) : UpbitRequestParam
