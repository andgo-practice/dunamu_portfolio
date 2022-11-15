package andgo.dunamuportfolio.ui

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.model.mockPriceList
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CoinPriceList(
    unit: CoinPriceUnit,
    list: List<UpbitCoinModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { coinModel -> coinModel.type.coinCode }
        ) { task ->
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
        list = mockPriceList,
        unit = CoinPriceUnit.KRW
    )
}
