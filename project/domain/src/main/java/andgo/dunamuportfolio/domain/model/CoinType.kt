package andgo.dunamuportfolio.domain.model

import androidx.compose.runtime.Stable

@Stable
enum class CoinType(
    val coinCode: String,
    val coinName: String,
    val coinNameEng: String,
) {
    XRP("XRP", "리플", "Ripple"),
    DOGE("DOGE", "도지코인", "Doge"),
    SOL("SOL", "솔라나", "Solana"),
    BTC("BTC", "비트코인", "Bitcoin"),
    ETH("ETH", "이더리움", "Ethereum"),
    FLOW("FLOW", "플로우", "Flow");

    companion object {
        fun from(code: String): CoinType? {
            return values().find { it.coinCode == code }
        }
    }
}