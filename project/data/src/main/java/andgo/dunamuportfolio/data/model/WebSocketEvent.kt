package andgo.dunamuportfolio.data.model

sealed interface WebSocketEvent {
    object ConnectionOpened : WebSocketEvent
    object MessageReceived : WebSocketEvent
    data class ConnectionClosing(val cause: String) : WebSocketEvent
    data class ConnectionClosed(val cause: String) : WebSocketEvent
    data class ConnectionFailed(val error: Throwable) : WebSocketEvent
}
