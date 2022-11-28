package andgo.dunamuportfolio.data.model

import andgo.dunamuportfolio.domain.model.CoinPriceUnit

enum class CoinPriceUnitRemote(val value: String) {
    KRW("KRW"),
    BTC("BTC"),
    USDT("USDT")
}

fun CoinPriceUnit.toCoinUnitRemote(): CoinPriceUnitRemote {
    return when (this) {
        CoinPriceUnit.KRW -> CoinPriceUnitRemote.KRW
        CoinPriceUnit.BTC -> CoinPriceUnitRemote.BTC
        CoinPriceUnit.USDT -> CoinPriceUnitRemote.USDT
    }
}