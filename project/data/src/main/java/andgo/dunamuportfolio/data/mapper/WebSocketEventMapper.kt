package andgo.dunamuportfolio.data.mapper

import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import andgo.dunamuportfolio.domain.model.UpbitWebSocketEvent
import javax.inject.Inject

class WebSocketEventMapper @Inject constructor() {
    fun map(event: WebSocketEvent): UpbitWebSocketEvent {
        return when (event) {
            is WebSocketEvent.ConnectionClosed -> UpbitWebSocketEvent.ConnectionClosed(event.cause)
            is WebSocketEvent.ConnectionClosing -> UpbitWebSocketEvent.ConnectionClosing(event.cause)
            is WebSocketEvent.ConnectionFailed -> UpbitWebSocketEvent.ConnectionFailed(event.error)
            WebSocketEvent.ConnectionOpened -> UpbitWebSocketEvent.ConnectionOpened
            is WebSocketEvent.MessageReceived -> UpbitWebSocketEvent.MessageReceived(event.model.toUpbitCoinModel())
        }
    }
}