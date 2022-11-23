package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.ChangeStatus
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import javax.inject.Inject

class CoinPriceListProvider @Inject constructor() {
    private val coinPriceMap = mutableMapOf<CoinType, UpbitCoinModel>()

    val initialPriceList
        get() = CoinType.values().map {
            UpbitCoinModel(
                type = it,
                tradePrice = 0.0,
                changeStatus = ChangeStatus.SAME,
                accTradePricePerMillion = 0.0,
                changeRate = 0.0,
                changePrice = 0.0
            )
        }


    init {
        initialPriceList.forEach { coinPriceMap[it.type] = it }
    }

    fun updateAndGet(upbitCoinModel: UpbitCoinModel): List<UpbitCoinModel> {
        coinPriceMap[upbitCoinModel.type] = upbitCoinModel
        return coinPriceMap.map { it.value }.toList()
    }
}