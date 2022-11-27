package andgo.dunamuportfolio.data.remote

import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import andgo.dunamuportfolio.data.remote.service.UpbitService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UpbitRemoteImpl @Inject constructor(
    private val upbitService: UpbitService
) : UpbitRemote {
    override fun connect(): Flow<WebSocketEvent> {
        return upbitService.connect()
    }

    override fun disconnect() {
        upbitService.disconnect()
    }

    override fun subscribe(data: SubscribeData) {
        upbitService.subscribe(data.params)
    }
}