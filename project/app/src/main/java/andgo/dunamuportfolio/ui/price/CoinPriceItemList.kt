package andgo.dunamuportfolio.ui.price

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
    isDescriptionKorean: Boolean,
    unit: CoinPriceUnit,
    modifier: Modifier = Modifier,
    coinList: List<UpbitCoinModel>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = coinList,
            key = { coinModel -> coinModel.type }
        ) { task ->
            CoinPriceItem(
                isDescriptionKorean = isDescriptionKorean,
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
        isDescriptionKorean = true,
        modifier = Modifier.background(color = Color.White),
        unit = CoinPriceUnit.KRW,
        coinList = mockPriceList
    )
}
