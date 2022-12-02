package andgo.dunamuportfolio.feature_coinprice.ui.price

import andgo.dunamuportfolio.feature_coinprice.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinSearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChanged,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                stringResource(id = R.string.search_hint),
                style = TextStyle(fontSize = 12.sp)
            )
        },
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(fontSize = 12.sp)
    )
}

@Preview(showBackground = true)
@Composable
fun CoinSearchBarPreview() {
    CoinSearchBar(text = "", onValueChanged = {})
}