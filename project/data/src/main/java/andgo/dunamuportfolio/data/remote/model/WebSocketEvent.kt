package andgo.dunamuportfolio.data.remote.model

sealed interface WebSocketEvent {
    object ConnectionOpened : WebSocketEvent
    data class MessageReceived(val model: UpbitCoinRemoteModel) : WebSocketEvent
    data class ConnectionClosing(val cause: String) : WebSocketEvent
    data class ConnectionClosed(val cause: String) : WebSocketEvent
    data class ConnectionFailed(val error: Throwable) : WebSocketEvent
}
