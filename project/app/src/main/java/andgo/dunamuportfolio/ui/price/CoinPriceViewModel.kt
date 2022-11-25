package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.*
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import andgo.dunamuportfolio.domain.usecase.GetCoinPriceUseCase
import andgo.dunamuportfolio.domain.usecase.SubscribeCoinUseCase
import andgo.dunamuportfolio.domain.usecase.core.data
import andgo.dunamuportfolio.ui.price.helper.CoinInitialStateProvider
import andgo.dunamuportfolio.ui.price.helper.CoinPriceListRetriever
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val getCoinPriceUseCase: GetCoinPriceUseCase,
    private val subscribeCoinUseCase: SubscribeCoinUseCase,
    private val coinPriceListRetriever: CoinPriceListRetriever,
    initialStateProvider: CoinInitialStateProvider
) : ViewModel() {

    init {
        observeCoinPrice()
        requestCoinList()
    }

    private val _header = MutableStateFlow(initialStateProvider.initialHeader)
    private val _coinPriceMap = MutableStateFlow(initialStateProvider.initialCoinMap)


    val header
        get() = _header.asStateFlow()

    val coinPriceList: Flow<List<UpbitCoinModel>>
        get() = combine(_header, _coinPriceMap) { header, priceMap ->
            coinPriceListRetriever.create(
                priceMap = priceMap,
                header = header
            )
        }


    private fun observeCoinPrice() {
        viewModelScope.launch {
            getCoinPriceUseCase(Unit)
                .mapNotNull { it.data }
                .collect { upbitModel ->
                    _coinPriceMap.update {
                        it.toMutableMap().apply { this[upbitModel.type] = upbitModel }
                    }
                }
        }
    }

    private fun requestCoinList() {
        viewModelScope.launch {
            delay(5000) // TODO websocketEvent가 오기전까진 delay로 대응

            subscribeCoinUseCase(
                CoinSubscribeParam(
                    coinTypeParams = CoinType.values().toList(),
                    coinPriceUnit = CoinPriceUnit.KRW
                )
            )
        }
    }

    fun onClickDescription() {
        viewModelScope.launch {
            _header.update { it.copy(isCoinDescriptionKorean = it.isCoinDescriptionKorean.not()) }
        }
    }

    fun onClickSortType(sortType: SortType) {
        viewModelScope.launch {
            _header.update {
                if (it.sortType == sortType) it.copy(isSortDescending = it.isSortDescending.not())
                else it.copy(sortType = sortType, isSortDescending = true)
            }
        }
    }
}