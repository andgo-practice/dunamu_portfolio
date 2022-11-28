package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.SortType
import andgo.dunamuportfolio.domain.model.mockPriceList
import andgo.dunamuportfolio.ui.price.CoinPriceHeaderItem
import andgo.dunamuportfolio.ui.price.CoinPriceList
import andgo.dunamuportfolio.ui.price.CoinPriceViewModel
import andgo.dunamuportfolio.ui.price.model.CoinPriceHeader
import andgo.dunamuportfolio.ui.theme.DunamuPortfolioTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CoinPriceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContent()
        launchWebSocketConnect()
    }

    private fun initContent() {
        setContent {
            DunamuPortfolioTheme {
                UpbitMain()
            }
        }
    }

    private fun launchWebSocketConnect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.connect() }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.disconnect()
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