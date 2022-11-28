package andgo.dunamuportfolio.data.remote

import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import kotlinx.coroutines.flow.Flow

interface UpbitRemote {
    fun subscribe(data: SubscribeData)
    fun connect(): Flow<WebSocketEvent>
    fun disconnect()
}