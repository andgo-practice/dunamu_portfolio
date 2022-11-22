package andgo.dunamuportfolio.domain.model

enum class CoinType(
    val coinCode: String,
    val coinName: String
) {
    XRP("XRP", "리플"),
    DOGE("DOGE", "도지코인"),
    SOL("SOL", "솔라나"),
    BTC("BTC", "비트코인"),
    ETH("ETH", "이더리움"),
    FLOW("FLOW", "플로우");

    companion object {
        fun from(code: String): CoinType? {
            return values().find { it.coinCode == code }
        }
    }
}