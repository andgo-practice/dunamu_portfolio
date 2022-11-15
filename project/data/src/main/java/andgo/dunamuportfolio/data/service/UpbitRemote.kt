package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.model.WebSocketEvent
import kotlinx.coroutines.flow.Flow

interface UpbitRemote {
    val events: Flow<WebSocketEvent>
    val upbitModel: Flow<UpbitCoinRemoteModel>

    fun subscribe(data: SubscribeData)
}