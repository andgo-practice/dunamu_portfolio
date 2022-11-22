package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.domain.model.CoinPriceUnit

fun CoinPriceUnit.toCoinUnitRemote(): CoinPriceUnitRemote {
    return when(this) {
        CoinPriceUnit.KRW -> CoinPriceUnitRemote.KRW
        CoinPriceUnit.BTC -> CoinPriceUnitRemote.BTC
        CoinPriceUnit.USDT -> CoinPriceUnitRemote.USDT
    }
}