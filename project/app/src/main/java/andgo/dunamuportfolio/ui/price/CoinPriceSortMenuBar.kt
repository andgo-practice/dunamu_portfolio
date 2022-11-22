package andgo.dunamuportfolio.ui.price

import andgo.dunamuportfolio.R
import andgo.dunamuportfolio.domain.model.SortType
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinPriceSortMenuBar(
    modifier: Modifier = Modifier,
    isKoreanMode: Boolean,
    sortType: SortType = SortType.TRADE,
    isSortDescending: Boolean = true
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(modifier = Modifier.weight(2f)) {
            Text(
                text = stringResource(id = if (isKoreanMode) R.string.menu_coin_korean else R.string.menu_coin_english),
                style = TextStyle(fontSize = 13.sp)
            )
        }

        SortButton(
            modifier = Modifier.weight(1f),
            currentSortType = sortType,
            isSortDescending = isSortDescending,
            targetSortTextRes = R.string.menu_coin_sort_price,
            targetSortType = SortType.PRICE,
        )

        SortButton(
            modifier = Modifier.weight(1f),
            currentSortType = sortType,
            isSortDescending = isSortDescending,
            targetSortTextRes = R.string.menu_coin_sort_rate,
            targetSortType = SortType.RATE,
        )

        SortButton(
            modifier = Modifier.weight(1f),
            currentSortType = sortType,
            isSortDescending = isSortDescending,
            targetSortTextRes = R.string.menu_coin_sort_trade,
            targetSortType = SortType.TRADE,
        )

    }
}

@Composable
fun SortButton(
    modifier: Modifier,
    currentSortType: SortType,
    isSortDescending: Boolean,
    @StringRes targetSortTextRes: Int,
    targetSortType: SortType
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = stringResource(id = targetSortTextRes),
            style = TextStyle(fontSize = 13.sp)
        )

        Column(
            modifier = Modifier.offset(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (!isSortDescending && currentSortType == targetSortType) "▲" else "△",
                style = TextStyle(fontSize = 7.sp)
            )
            Text(
                text = if (isSortDescending && currentSortType == targetSortType) "▼" else "▽",
                style = TextStyle(fontSize = 7.sp)
            )
        }
    }
}

@Preview
@Composable
fun CoinPriceSortMenuBarPreview() {
    CoinPriceSortMenuBar(isKoreanMode = true, sortType = SortType.TRADE)
}
