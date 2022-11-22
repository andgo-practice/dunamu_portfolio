package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.model.mockPriceList
import andgo.dunamuportfolio.ui.price.CoinPriceViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CoinPriceList(
    unit: CoinPriceUnit,
    modifier: Modifier = Modifier,
    coinPriceList: List<UpbitCoinModel>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = coinPriceList,
            key = { coinModel -> coinModel.type }
        ) { task ->

            // TODO recomposition 개선해야합니다.
            Log.d("Test", "task:${task.type.ordinal} composition")
            CoinPriceItem(
                unit = unit,
                coin = task
            )

            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}

@Preview
@Composable
fun CoinPriceListPreview() {
    CoinPriceList(
        modifier = Modifier.background(color = Color.White),
        unit = CoinPriceUnit.KRW,
        coinPriceList = mockPriceList
    )
}
