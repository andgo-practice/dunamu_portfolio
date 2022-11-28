package andgo.dunamuportfolio.data.remote.service

import andgo.dunamuportfolio.data.remote.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import andgo.dunamuportfolio.data.remote.service.websocket.UpbitWebSocketHandler
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UpbitServiceImpl @Inject constructor(
    private val upbitWebSocketHandler: UpbitWebSocketHandler
) : UpbitService {

    override val events: Flow<WebSocketEvent>
        get() = upbitWebSocketHandler.event.receiveAsFlow()

    override val coinResponse: Flow<UpbitCoinRemoteModel>
        get() = upbitWebSocketHandler.event.receiveAsFlow()
            .filterIsInstance<WebSocketEvent.MessageReceived>()
            .map { it.model }

    override fun connect(): Flow<WebSocketEvent> {
        return upbitWebSocketHandler.connect()
    }

    override fun disconnect() {
        upbitWebSocketHandler.cancel()
    }

    override fun subscribe(params: List<Any>) {
        upbitWebSocketHandler.send(params)
    }
}