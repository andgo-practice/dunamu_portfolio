package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.ui.price.CoinPriceHeaderItem
import andgo.dunamuportfolio.ui.price.CoinPriceList
import andgo.dunamuportfolio.ui.price.CoinPriceViewModel
import andgo.dunamuportfolio.ui.price.CoinSearchBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpbitMain(viewModel: CoinPriceViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            CoinSearchBar(
                text = viewModel.searchText.collectAsState().value,
                onValueChanged = viewModel::onSearchTextChanged
            )

            val header = viewModel.header.collectAsState().value

            CoinPriceHeaderItem(
                header = header,
                onClickSort = viewModel::onClickSortType,
                onClickDescription = viewModel::onClickDescription
            )
            CoinPriceList(
                isDescriptionKorean = header.isCoinDescriptionKorean,
                unit = CoinPriceUnit.KRW,
                coinPriceList = viewModel.coinPriceList.collectAsState(listOf()).value
            )
        }
    }
}