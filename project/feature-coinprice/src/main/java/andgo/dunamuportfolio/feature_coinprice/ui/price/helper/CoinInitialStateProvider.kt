package andgo.dunamuportfolio.feature_coinprice.ui.price.helper

import andgo.dunamuportfolio.domain.model.ChangeStatus
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.feature_coinprice.ui.price.model.CoinPriceHeader
import javax.inject.Inject

class CoinInitialStateProvider @Inject constructor() {
    val initialSearchText get() = ""

    val initialCoinMap
        get() = mutableMapOf<CoinType, UpbitCoinModel>().apply {
            CoinType.values().forEach {
                this[it] = UpbitCoinModel(
                    type = it,
                    tradePrice = 0.0,
                    changeStatus = ChangeStatus.SAME,
                    accTradePricePerMillion = 0.0,
                    changeRate = 0.0,
                    changePrice = 0.0
                )
            }
        }

    val initialHeader
        get() = CoinPriceHeader(
            isCoinDescriptionKorean = true,
            sortType = SortType.TRADE,
            isSortDescending = true
        )
}