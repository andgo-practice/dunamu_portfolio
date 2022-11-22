package andgo.dunamuportfolio.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpbitCoinRemoteModel(
    @Json(name = "type") val type: String,
    @Json(name = "code") val code: String,
    @Json(name = "opening_price") val openingPrice: Double,
    @Json(name = "high_price") val highPrice: Double,
    @Json(name = "low_price") val lowPrice: Double,
    @Json(name = "trade_price") val tradePrice: Double,
    @Json(name = "prev_closing_price") val prevClosingPrice: Double,
    @Json(name = "change") val change: String,
    @Json(name = "change_price") val changePrice: Double,
    @Json(name = "signed_change_price") val signedChangePrice: Double,
    @Json(name = "change_rate") val changeRate: Double,
    @Json(name = "signed_change_rate") val signedChangeRate: Double,
    @Json(name = "acc_trade_volume") val accTradeVolume: Double,
    @Json(name = "acc_trade_volume_24h") val accTradeVolume24H: Double,
    @Json(name = "acc_trade_price") val accTradePrice: Double,
    @Json(name = "acc_trade_price_24h") val accTradePrice24H: Double,
    @Json(name = "trade_date") val tradeDate: String,
    @Json(name = "trade_time") val tradeTime: Long,
    @Json(name = "trade_timestamp") val tradeTimeStamp: Long,
    @Json(name = "ask_bid") val askBid: String,
    @Json(name = "acc_ask_volume") val accAskVolume: Double,
    @Json(name = "acc_bid_volume") val accBidVolume: Double,
    @Json(name = "highest_52_week_price") val highest52WeekPrice: Double,
    @Json(name = "highest_52_week_date") val highest52WeekDate: String,
    @Json(name = "lowest_52_week_price") val lowest52WeekPrice: Double,
    @Json(name = "lowest_52_week_date") val lowest52WeekDate: String,
    @Json(name = "market_state") val marketState: String,
    @Json(name = "is_trading_suspended") val isTradingSuspended: Boolean,
    @Json(name = "market_warning") val marketWarning: String,
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "stream_type") val streamType: String,
)