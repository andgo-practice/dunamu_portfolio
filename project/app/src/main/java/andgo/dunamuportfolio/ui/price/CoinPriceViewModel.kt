package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.*
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import andgo.dunamuportfolio.domain.usecase.ConnectWebSocketUseCase
import andgo.dunamuportfolio.domain.usecase.SubscribeCoinUseCase
import andgo.dunamuportfolio.domain.usecase.core.data
import andgo.dunamuportfolio.ui.price.helper.CoinInitialStateProvider
import andgo.dunamuportfolio.ui.price.helper.CoinPriceListRetriever
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPriceViewModel @Inject constructor(
    private val subscribeCoinUseCase: SubscribeCoinUseCase,
    private val coinPriceListRetriever: CoinPriceListRetriever,
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
    initialStateProvider: CoinInitialStateProvider
) : ViewModel() {

    init { connectWebSocket() }

    private val _searchText = MutableStateFlow(initialStateProvider.initialSearchText)
    private val _header = MutableStateFlow(initialStateProvider.initialHeader)
    private val _coinPriceMap = MutableStateFlow(initialStateProvider.initialCoinMap)

    val searchText get() = _searchText.asStateFlow()
    val header get() = _header.asStateFlow()
    val coinPriceList: Flow<List<UpbitCoinModel>>
        get() = combine(_searchText, _header, _coinPriceMap) { searchText, header, priceMap ->
            coinPriceListRetriever.create(
                searchText = searchText,
                priceMap = priceMap,
                header = header
            )
        }


    private fun connectWebSocket() {
        connectWebSocketUseCase(Unit)
            .mapNotNull{ it.data }
            .onEach {
                when (it) {
                    is UpbitWebSocketEvent.ConnectionOpened -> requestCoinList()
                    is UpbitWebSocketEvent.MessageReceived -> updateCoinPriceModel(it.model)
                    else -> Unit
                }
            }
            .launchIn(viewModelScope)
    }

    private fun updateCoinPriceModel(upbitModel: UpbitCoinModel?) {
        if (upbitModel == null) return

        _coinPriceMap.update {
            it.toMutableMap().apply { this[upbitModel.type] = upbitModel }
        }
    }

    private fun requestCoinList() {
        viewModelScope.launch {
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

    fun onSearchTextChanged(input: String) {
        viewModelScope.launch {
            _searchText.update { input }
        }
    }
}