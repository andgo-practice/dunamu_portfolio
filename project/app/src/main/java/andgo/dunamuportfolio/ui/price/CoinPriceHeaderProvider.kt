package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.SortType
import javax.inject.Inject

class CoinPriceHeaderProvider @Inject constructor() {
    val initialHeader
        get() = CoinPriceHeader(
            isCoinDescriptionKorean = true,
            sortType = SortType.TRADE,
            isSortDescending = true
        )

    fun clickSort(sortType: SortType, header: CoinPriceHeader): CoinPriceHeader {
        return when (sortType) {
            header.sortType -> header.copy(isSortDescending = header.isSortDescending.not())
            else -> header.copy(sortType = sortType)
        }

    }

    fun clickDescription(header: CoinPriceHeader): CoinPriceHeader {
        return header.copy(isCoinDescriptionKorean = header.isCoinDescriptionKorean.not())
    }
}