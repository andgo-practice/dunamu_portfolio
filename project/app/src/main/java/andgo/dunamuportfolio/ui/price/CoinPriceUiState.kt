package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf

@Immutable
data class CoinPriceUiState(
    val coinPriceList: List<UpbitCoinModel> = listOf()
)