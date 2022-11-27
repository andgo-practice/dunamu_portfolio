package andgo.dunamuportfolio.domain

import andgo.dunamuportfolio.domain.model.UpbitWebSocketEvent
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import kotlinx.coroutines.flow.Flow

interface UpbitRepository {
    fun connect(): Flow<UpbitWebSocketEvent>
    fun disconnect()
    fun subscribe(coinSubscribeParam: CoinSubscribeParam)
}