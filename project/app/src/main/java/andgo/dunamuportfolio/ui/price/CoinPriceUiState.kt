package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf

@Immutable
data class CoinPriceUiState(
    val header: CoinPriceHeader,
    val coinPriceList: List<UpbitCoinModel> = listOf()
)

data class CoinPriceHeader(
    val isCoinDescriptionKorean: Boolean,
    val sortType: SortType,
    val isSortDescending: Boolean
)