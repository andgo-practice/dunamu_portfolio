package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.mockPriceList
import andgo.dunamuportfolio.ui.CoinPriceList
import andgo.dunamuportfolio.ui.theme.DunamuPortfolioTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinPriceFragment : Fragment() {
    private val viewModel: CoinPriceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO ViewPager로부터 값을 받아와야함.
        viewModel.setUnit(CoinPriceUnit.KRW)

        return ComposeView(requireContext()).apply {
            setContent {
                DunamuPortfolioTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        CoinPriceList(
                            unit = CoinPriceUnit.KRW,
                            coinPriceList = viewModel.uiState.collectAsState().value.coinPriceList
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinPriceFragmentPreview() {
    DunamuPortfolioTheme {
        CoinPriceList(unit = CoinPriceUnit.KRW, coinPriceList = mockPriceList)
    }
}