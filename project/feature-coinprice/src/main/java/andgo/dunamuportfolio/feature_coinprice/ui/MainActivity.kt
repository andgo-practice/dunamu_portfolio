package andgo.dunamuportfolio.feature_coinprice.ui

import andgo.dunamuportfolio.feature_coinprice.ui.UpbitMain
import andgo.dunamuportfolio.feature_coinprice.ui.price.CoinPriceViewModel
import andgo.dunamuportfolio.feature_coinprice.ui.theme.DunamuPortfolioTheme
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
            UpbitMain()
        }
    }
}