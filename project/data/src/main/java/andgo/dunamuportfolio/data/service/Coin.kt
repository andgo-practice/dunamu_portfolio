package andgo.dunamuportfolio.data.service


enum class Coin(
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

fun createCoinParams(unit: CoinUnit): List<String> {
    return Coin.values().map { "${unit.value}-${it.coinCode}" }.toList()
}