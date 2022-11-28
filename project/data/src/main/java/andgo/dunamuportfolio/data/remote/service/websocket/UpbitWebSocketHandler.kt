package andgo.dunamuportfolio.data.remote.service.websocket

import andgo.dunamuportfolio.data.BuildConfig
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class UpbitWebSocketHandler(
    private val moshi: Moshi,
    private val okHttpClient: OkHttpClient,
    private val webSocketHandler: UpbitWebSocketListener
) {
    val event get() = webSocketHandler.socketEventChannel

    private var webSocket: WebSocket? = null

    fun connect(): Flow<WebSocketEvent> {
        webSocket?.cancel()
        webSocket = okHttpClient.newWebSocket(
            Request.Builder().url(BuildConfig.SOCKET_ADDRESS).build(),
            webSocketHandler
        )

        return webSocketHandler.socketEventChannel.receiveAsFlow()
    }

    fun send(params: List<Any>) {
        moshi.adapter(List::class.java).toJson(params)?.let {
            webSocket?.send(it)
        }
    }

    fun cancel() = webSocket?.cancel()
}