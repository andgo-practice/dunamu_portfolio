package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.mockPriceList
import andgo.dunamuportfolio.ui.price.model.CoinPriceHeader
import andgo.dunamuportfolio.ui.price.CoinPriceHeaderItem
import andgo.dunamuportfolio.ui.price.CoinPriceList
import andgo.dunamuportfolio.ui.theme.DunamuPortfolioTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DunamuPortfolioTheme {
                UpbitMain()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    DunamuPortfolioTheme {
        Column {
            CoinPriceHeaderItem(
                header = CoinPriceHeader(false, SortType.TRADE, false),
                onClickSort = {},
                onClickDescription = {})
            CoinPriceList(
                isDescriptionKorean = true,
                unit = CoinPriceUnit.KRW,
                coinPriceList = mockPriceList
            )
        }
    }
}