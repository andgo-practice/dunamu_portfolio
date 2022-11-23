package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import andgo.dunamuportfolio.domain.usecase.GetCoinPriceUseCase
import andgo.dunamuportfolio.domain.usecase.SubscribeCoinUseCase
import andgo.dunamuportfolio.domain.usecase.core.data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val uiStateProvider: CoinPriceUiStateProvider,
    private val getCoinPriceUseCase: GetCoinPriceUseCase,
    private val subscribeCoinUseCase: SubscribeCoinUseCase
) : ViewModel() {

    init {
        observeCoinPrice()
    }

    private

    private fun observeCoinPrice() {
        viewModelScope.launch {
            getCoinPriceUseCase(Unit)
                .mapNotNull { it.data }
                .map (uiStateProvider::updatePrice)
                .collect { _uiState.emit(it) }
        }
    }

    private val _uiState = MutableStateFlow(uiStateProvider.initialUiState)
    val uiState get() = _uiState.asStateFlow()

    // TODO 정렬시 개선 예정입니다.
    private val selectedCoinPriceUnit: MutableStateFlow<CoinPriceUnit> =
        MutableStateFlow(CoinPriceUnit.KRW)
            .apply {
                onEach { requestCoinList(it) }.launchIn(viewModelScope)
            }

    fun setUnit(coinPriceUnit: CoinPriceUnit) {
        viewModelScope.launch {
            selectedCoinPriceUnit.emit(coinPriceUnit)
        }
    }

    private fun requestCoinList(coinPriceUnit: CoinPriceUnit) {
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