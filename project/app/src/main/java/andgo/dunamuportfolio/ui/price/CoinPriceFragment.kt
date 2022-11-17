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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment

class CoinPriceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinPriceFragmentPreview() {
    DunamuPortfolioTheme {
        CoinPriceList(unit = CoinPriceUnit.KRW, list = mockPriceList)
    }
}