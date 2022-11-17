package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.domain.model.CoinType

fun CoinType.toCoinTypeRemote(): CoinTypeRemote {
    return when(this) {
        CoinType.XRP -> CoinTypeRemote.XRP
        CoinType.DOGE -> CoinTypeRemote.DOGE
        CoinType.SOL -> CoinTypeRemote.SOL
        CoinType.BTC -> CoinTypeRemote.BTC
        CoinType.ETH -> CoinTypeRemote.ETH
        CoinType.FLOW -> CoinTypeRemote.FLOW
    }
}