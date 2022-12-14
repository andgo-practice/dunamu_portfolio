package andgo.dunamuportfolio.feature_coinprice.ui.price

import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.model.mockPriceList
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinPriceItem(
    isDescriptionKorean: Boolean,
    unit: CoinPriceUnit,
    coin: UpbitCoinModel,
    modifier: Modifier = Modifier
) {
    Log.d("Test", "task:${coin.type} composition")

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = if (isDescriptionKorean) coin.type.coinName else coin.type.coinNameEng,
                style = TextStyle(fontSize = 13.sp)
            )
            Text(
                text = "${coin.type.coinCode}/${unit.value}",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = TextStyle(
                fontSize = 13.sp,
                color = getColor(coin.changeRate)
            ),
            text = if (coin.tradePrice < 100) String.format("%.1f", coin.tradePrice)
            else String.format("%,d", coin.tradePrice.toInt())
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 13.sp,
                    color = getColor(coin.changeRate)
                ),
                text = "${String.format("%.2f", coin.changeRate)}%"
            )
            Text(
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 13.sp,
                    color = getColor(coin.changeRate)
                ),
                text = when {
                    kotlin.math.abs(coin.changePrice) >= 100 -> String.format(
                        "%,d",
                        coin.changePrice.toInt()
                    )
                    kotlin.math.abs(coin.changePrice) < 100 && kotlin.math.abs(coin.changePrice) >= 1 -> String.format(
                        "%.2f",
                        coin.changePrice
                    )
                    else -> String.format("%.4f", coin.changePrice)
                }
            )
        }


        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
            style = TextStyle(fontSize = 11.sp),
            text = "${String.format("%,d", coin.accTradePricePerMillion.toInt())}??????"
        )
    }
}

private fun getColor(diff: Double): Color {
    return when {
        diff > 0 -> Color.Red
        diff == 0.0 -> Color.Black
        else -> Color.Blue
    }
}

@Composable
@Preview
fun CoinPriceItemPreview() {
    CoinPriceItem(
        isDescriptionKorean = true,
        unit = CoinPriceUnit.KRW,
        coin = mockPriceList[0],
        modifier = Modifier.background(Color.White)
    )
}