package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.mockPriceList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import andgo.dunamuportfolio.ui.theme.DunamuPortfolioTheme
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DunamuPortfolioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinPriceList(unit = CoinPriceUnit.KRW, list = mockPriceList)
                }
            }
        }

        viewModel.subscribe()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DunamuPortfolioTheme {
        CoinPriceList(unit = CoinPriceUnit.KRW, list = mockPriceList)
    }
}