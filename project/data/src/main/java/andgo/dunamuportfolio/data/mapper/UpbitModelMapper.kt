package andgo.dunamuportfolio.data.mapper

import andgo.dunamuportfolio.data.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.domain.model.ChangeStatus
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.model.UpbitCoinModel

fun UpbitCoinRemoteModel.toUpbitCoinModel(): UpbitCoinModel? {
    return createCoinType(code)?.let { coinType ->
        UpbitCoinModel(
            type = coinType,
            tradePrice = tradePrice,
            changeRate = signedChangeRate * 100,
            changePrice = signedChangePrice,
            accTradePricePerMillion = accTradePrice24H / 1000000,
            changeStatus = when(change) {
                "RISE" -> ChangeStatus.UP
                "FALL" -> ChangeStatus.DOWN
                else -> ChangeStatus.SAME
            }
        )
    }
}

private fun createCoinType(codeWithUnit: String): CoinType? {
    return CoinType.from(codeWithUnit.split("-")[COIN_CODE_INDEX])
}

private const val COIN_CODE_INDEX = 1
