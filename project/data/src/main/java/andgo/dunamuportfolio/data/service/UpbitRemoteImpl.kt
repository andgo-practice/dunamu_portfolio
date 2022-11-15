package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.data.mapper.WebSocketEventMapper
import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.model.WebSocketEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UpbitRemoteImpl @Inject constructor(
    private val upbitService: UpbitService,
    private val eventMapper: WebSocketEventMapper,
) : UpbitRemote {

    override val events: Flow<WebSocketEvent>
        get() = upbitService.events.map(eventMapper::toSocketEvent)

    override val upbitModel: Flow<UpbitCoinRemoteModel>
        get() = upbitService.upbitPriceInfo

    override fun subscribe(data: SubscribeData) {
        upbitService.subscribe(data.params)
    }
}