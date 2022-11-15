package andgo.dunamuportfolio.data.mapper

import andgo.dunamuportfolio.data.model.WebSocketEvent
import android.util.Log
import com.tinder.scarlet.WebSocket
import javax.inject.Inject

// TODO 이벤트가 일로 안오는 이슈가 있어서 확인이 필요함.
internal class WebSocketEventMapper @Inject constructor() {
    fun toSocketEvent(event: WebSocket.Event): WebSocketEvent {
        Log.d("test", event.toString())
        return when (event) {
            is WebSocket.Event.OnConnectionOpened<*> -> WebSocketEvent.ConnectionOpened
            is WebSocket.Event.OnMessageReceived -> WebSocketEvent.MessageReceived
            is WebSocket.Event.OnConnectionClosing -> WebSocketEvent.ConnectionClosing(cause = event.shutdownReason.reason)
            is WebSocket.Event.OnConnectionClosed -> WebSocketEvent.ConnectionClosed(cause = event.shutdownReason.reason)
            is WebSocket.Event.OnConnectionFailed -> WebSocketEvent.ConnectionFailed(error = event.throwable)
        }
    }
}