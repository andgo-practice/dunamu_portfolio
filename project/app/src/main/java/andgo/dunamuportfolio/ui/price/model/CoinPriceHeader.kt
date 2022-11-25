package andgo.dunamuportfolio.ui.price.model

import andgo.dunamuportfolio.domain.model.SortType

data class CoinPriceHeader(
    val isCoinDescriptionKorean: Boolean,
    val sortType: SortType,
    val isSortDescending: Boolean
)