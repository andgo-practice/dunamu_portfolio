package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import javax.inject.Inject

class CoinPriceUiStateProvider @Inject constructor(
    private val coinPriceListProvider: CoinPriceListProvider,
    private val coinPriceHeaderProvider: CoinPriceHeaderProvider
) {
    var lastUiState = initialUiState // TODO 이걸 여기서 관리안하는 형태 고려

    val initialUiState: CoinPriceUiState
        get() = createInitialUiState()

    private fun createInitialUiState() = CoinPriceUiState(
        coinPriceList = coinPriceListProvider.initialPriceList,
        header = coinPriceHeaderProvider.initialHeader
    )

    fun updatePrice(
        upbitCoinModel: UpbitCoinModel,
    ): CoinPriceUiState {
        return lastUiState.updateAndCopy(
            coinPriceList = coinPriceListProvider.updateAndGet(
                upbitCoinModel = upbitCoinModel
            )
        )
    }

    fun updateHeaderSort(
        header: CoinPriceHeader,
        sortType: SortType,
    ): CoinPriceUiState {
        return lastUiState.updateAndCopy(
            header = coinPriceHeaderProvider.clickSort(
                sortType = sortType,
                header = header
            )
        )
    }

    fun updateHeaderDescription(
        header: CoinPriceHeader,
    ): CoinPriceUiState {
        return lastUiState.updateAndCopy(header = coinPriceHeaderProvider.clickDescription(header))
    }

    private fun CoinPriceUiState.updateAndCopy(
        header: CoinPriceHeader? = null,
        coinPriceList: List<UpbitCoinModel>? = null
    ): CoinPriceUiState {
        return when {
            header != null && coinPriceList == null -> lastUiState.copy(header = header)
            header == null && coinPriceList != null -> lastUiState.copy(coinPriceList = coinPriceList)
            header != null && coinPriceList != null -> lastUiState.copy(
                header = header,
                coinPriceList = coinPriceList
            )
            else -> throw IllegalAccessException()
        }.also {
            lastUiState = it
        }

    }
}