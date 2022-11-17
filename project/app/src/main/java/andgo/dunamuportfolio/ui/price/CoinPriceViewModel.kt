package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import andgo.dunamuportfolio.domain.usecase.GetCoinPriceUseCase
import andgo.dunamuportfolio.domain.usecase.SubscribeCoinUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val getCoinPriceUseCase: GetCoinPriceUseCase,
    private val subscribeCoinUseCase: SubscribeCoinUseCase
) : ViewModel() {

    // TODO 이런 방식이 아닌 ui state로 대응
    private val selectedCoinPriceUnit = MutableStateFlow(CoinPriceUnit.KRW)
        .onEach { subscribe(it) }

    private val coinModelFlow = getCoinPriceUseCase(Unit)

    private fun subscribe(coinPriceUnit: CoinPriceUnit) {
        viewModelScope.launch {
            subscribeCoinUseCase(
                CoinSubscribeParam(
                    coinTypeParams = CoinType.values().toList(),
                    coinPriceUnit = coinPriceUnit
                )
            )
        }
    }
}