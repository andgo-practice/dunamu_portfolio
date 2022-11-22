package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.ChangeStatus
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import javax.inject.Inject

class CoinPriceUiStateProvider @Inject constructor() {
    private val coinPriceMap = mutableMapOf<CoinType, UpbitCoinModel>()

    val initialUiState: CoinPriceUiState
        get() = createInitialUiState()

    init {
        initialPriceMap()
    }

    private fun createInitialUiState() = CoinType.values().map {
        UpbitCoinModel(
            type = it,
            tradePrice = 0.0,
            changeStatus = ChangeStatus.SAME,
            accTradePricePerMillion = 0.0,
            changeRate = 0.0,
            changePrice = 0.0
        )
    }.let { CoinPriceUiState(it) }

    private fun initialPriceMap() {
        initialUiState
            .coinPriceList
            .forEach { coinPriceMap[it.type] = it }
    }

    fun updateAndCreateUiState(
        upbitCoinModel: UpbitCoinModel
    ): CoinPriceUiState {
        coinPriceMap[upbitCoinModel.type] = upbitCoinModel
        return CoinPriceUiState(
            coinPriceList = coinPriceMap.map { it.value }.toList()
        )
    }
}