package andgo.dunamuportfolio.data.remote.service

import andgo.dunamuportfolio.data.remote.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.remote.model.WebSocketEvent
import kotlinx.coroutines.flow.Flow

internal interface UpbitService {

    val events: Flow<WebSocketEvent>

    val coinResponse: Flow<UpbitCoinRemoteModel>

    fun connect(): Flow<WebSocketEvent>

    fun subscribe(params: List<Any>)

    fun disconnect()
}