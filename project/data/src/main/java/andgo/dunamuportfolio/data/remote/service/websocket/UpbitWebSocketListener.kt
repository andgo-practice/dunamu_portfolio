package andgo.dunamuportfolio.data.remote.service.websocket

import andgo.dunamuportfolio.data.remote.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import andgo.dunamuportfolio.domain.di.ExternalCoroutineScope
import android.util.Log
import com.squareup.moshi.Moshi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.RENDEZVOUS
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class UpbitWebSocketListener @Inject constructor(
    private val moshi: Moshi,
    private val externalCoroutineScope: ExternalCoroutineScope
) : WebSocketListener() {

    val socketEventChannel = Channel<WebSocketEvent>(RENDEZVOUS)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d("UpbitWebSocketListener", "onOpen")
        externalCoroutineScope.scope.launch {
            socketEventChannel.send(WebSocketEvent.ConnectionOpened)
        }
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        externalCoroutineScope.scope.launch {
            moshi.adapter(UpbitCoinRemoteModel::class.java).fromJson(bytes.utf8())
                ?.let {
                    Log.d("UpbitWebSocketListener", "onMessage:$it")
                    socketEventChannel.send(WebSocketEvent.MessageReceived(it))
                }
        }
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.d("UpbitWebSocketListener", "onClosed")
        externalCoroutineScope.scope.launch {
            socketEventChannel.send(WebSocketEvent.ConnectionClosed(reason))
        }

        socketEventChannel.close()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d("UpbitWebSocketListener", "onFailure")
        externalCoroutineScope.scope.launch {
            socketEventChannel.send(WebSocketEvent.ConnectionFailed(t))
        }
    }
}