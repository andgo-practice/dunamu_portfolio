package andgo.dunamuportfolio.domain.model

sealed interface UpbitWebSocketEvent {
    object ConnectionOpened : UpbitWebSocketEvent
    data class MessageReceived(val model: UpbitCoinModel?) : UpbitWebSocketEvent
    data class ConnectionClosing(val cause: String) : UpbitWebSocketEvent
    data class ConnectionClosed(val cause: String) : UpbitWebSocketEvent
    data class ConnectionFailed(val error: Throwable) : UpbitWebSocketEvent
}
