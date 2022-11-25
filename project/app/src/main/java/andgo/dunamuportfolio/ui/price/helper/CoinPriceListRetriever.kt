package andgo.dunamuportfolio.ui.price.helper

import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.ui.price.model.CoinPriceHeader
import javax.inject.Inject

class CoinPriceListRetriever @Inject constructor() {
    fun create(
        priceMap: Map<CoinType, UpbitCoinModel>,
        header: CoinPriceHeader
    ) = priceMap
        .map { it.value }
        .sortedByDescending { coinModel ->
            when (header.sortType) {
                SortType.PRICE -> coinModel.changePrice
                SortType.RATE -> coinModel.changeRate
                SortType.TRADE -> coinModel.accTradePricePerMillion
            }.let { if (header.isSortDescending) it else -it }
        }

}