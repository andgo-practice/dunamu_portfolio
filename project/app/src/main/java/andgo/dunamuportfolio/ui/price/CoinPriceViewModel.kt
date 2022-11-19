package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import andgo.dunamuportfolio.domain.usecase.GetCoinPriceUseCase
import andgo.dunamuportfolio.domain.usecase.SubscribeCoinUseCase
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val getCoinPriceUseCase: GetCoinPriceUseCase,
    private val subscribeCoinUseCase: SubscribeCoinUseCase
) : ViewModel() {

    // TODO 이런 방식이 아닌 ui state로 대응
    private val selectedCoinPriceUnit: MutableStateFlow<CoinPriceUnit> =
        MutableStateFlow(CoinPriceUnit.KRW)
            .apply {
                onEach { subscribe(it) }
                    .launchIn(viewModelScope)
            }

    val coinModelFlow = getCoinPriceUseCase(Unit)

    fun setUnit(coinPriceUnit: CoinPriceUnit) {
        viewModelScope.launch {
            selectedCoinPriceUnit.emit(coinPriceUnit)
        }
    }

    private fun subscribe(coinPriceUnit: CoinPriceUnit) {
        viewModelScope.launch {
            delay(5000) // TODO websocketEvent가 오기전까진 delay로 대응
            subscribeCoinUseCase(
                CoinSubscribeParam(
                    coinTypeParams = CoinType.values().toList(),
                    coinPriceUnit = coinPriceUnit
                )
            )
        }
    }
}