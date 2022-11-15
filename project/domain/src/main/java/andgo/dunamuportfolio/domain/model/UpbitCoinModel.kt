package andgo.dunamuportfolio.domain.model

data class UpbitCoinModel(
    val type: CoinType,
    val tradePrice: Double,
    val changeRate: Double,
    val accTradeVolume: Double,
    val changeStatus: ChangeStatus
)

enum class ChangeStatus {
    UP, SAME, DOWN
}

val mockPriceList = listOf(
    UpbitCoinModel(
        type = CoinType.BTC,
        tradePrice = 416.0,
        changeRate = -18.91,
        accTradeVolume = 6083525.0,
        changeStatus = ChangeStatus.DOWN
    ),

    UpbitCoinModel(
        type = CoinType.DOGE,
        tradePrice = 1675.0,
        changeRate = 0.89,
        accTradeVolume = 2796056.0,
        changeStatus = ChangeStatus.UP
    )
)