package andgo.dunamuportfolio.feature_coinprice.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.feature_coinprice.ui.price.CoinPriceHeaderItem
import andgo.dunamuportfolio.feature_coinprice.ui.price.CoinPriceList
import andgo.dunamuportfolio.feature_coinprice.ui.price.CoinPriceViewModel
import andgo.dunamuportfolio.feature_coinprice.ui.price.CoinSearchBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpbitMain(viewModel: CoinPriceViewModel = viewModel()) {

    val text by viewModel.searchText.collectAsState()
    val header by viewModel.header.collectAsState()
    val coinList by viewModel.coinPriceList.collectAsState(listOf())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            CoinSearchBar(
                text = text,
                onValueChanged = viewModel::onSearchTextChanged
            )

            CoinPriceHeaderItem(
                header = header,
                onClickSort = viewModel::onClickSortType,
                onClickDescription = viewModel::onClickDescription
            )
            CoinPriceList(
                isDescriptionKorean = header.isCoinDescriptionKorean,
                unit = CoinPriceUnit.KRW,
                coinList = coinList
            )
        }
    }
}