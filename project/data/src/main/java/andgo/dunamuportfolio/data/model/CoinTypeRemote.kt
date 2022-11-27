package andgo.dunamuportfolio.data.model

import andgo.dunamuportfolio.domain.model.CoinType


enum class CoinTypeRemote(
    val coinCode: String,
    val coinName: String
) {
    XRP("XRP", "리플"),
    DOGE("DOGE", "도지코인"),
    SOL("SOL", "솔라나"),
    BTC("BTC", "비트코인"),
    ETH("ETH", "이더리움"),
    FLOW("FLOW", "플로우");
}

fun List<CoinTypeRemote>.toRequestParams(unit: CoinPriceUnitRemote): List<String> {
    return this.map { "${unit.value}-${it.coinCode}" }.toList()
}

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
