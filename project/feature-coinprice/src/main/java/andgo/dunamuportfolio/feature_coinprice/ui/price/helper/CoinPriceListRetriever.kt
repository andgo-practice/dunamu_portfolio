package andgo.dunamuportfolio.feature_coinprice.ui.price.helper

import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.feature_coinprice.ui.price.model.CoinPriceHeader
import javax.inject.Inject

class CoinPriceListRetriever @Inject constructor() {
    fun create(
        searchText: String,
        priceMap: Map<CoinType, UpbitCoinModel>,
        header: CoinPriceHeader
    ) = priceMap
        .map { it.value }
        .filter { it.isInSearch(searchText) }
        .sortedByDescending { coinModel ->
            when (header.sortType) {
                SortType.PRICE -> coinModel.changePrice
                SortType.RATE -> coinModel.changeRate
                SortType.TRADE -> coinModel.accTradePricePerMillion
            }.let { if (header.isSortDescending) it else -it }
        }
}

fun UpbitCoinModel.isInSearch(searchText: String): Boolean {
    val lowerSearchText = searchText.lowercase()
    return type.coinName.lowercase().contains(lowerSearchText)
            || type.coinNameEng.lowercase().contains(lowerSearchText)
            || type.coinCode.lowercase().contains(lowerSearchText)
}