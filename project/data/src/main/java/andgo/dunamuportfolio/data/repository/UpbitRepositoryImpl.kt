package andgo.dunamuportfolio.data.repository

import andgo.dunamuportfolio.data.mapper.WebSocketEventMapper
import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.model.SubscribeRequestType
import andgo.dunamuportfolio.data.model.Ticket
import andgo.dunamuportfolio.data.model.Type
import andgo.dunamuportfolio.data.remote.UpbitRemote
import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.UpbitWebSocketEvent
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class UpbitRepositoryImpl @Inject constructor(
    private val upbitRemote: UpbitRemote,
    private val webSocketEventMapper: WebSocketEventMapper
) : UpbitRepository {

    override fun connect(): Flow<UpbitWebSocketEvent> {
        return upbitRemote.connect().map(webSocketEventMapper::map)
    }

    override fun disconnect() {
        upbitRemote.disconnect()
    }

    override fun subscribe(coinSubscribeParam: CoinSubscribeParam) {
        upbitRemote.subscribe(
            SubscribeData.create(
                ticket = Ticket(ticket = UUID.randomUUID().toString()),
                type = Type(
                    type = SubscribeRequestType.Ticker.value,
                    codes = coinSubscribeParam.coinTypeList
                        .asSequence()
                        .map { "${coinSubscribeParam.coinPriceUnit}-${it.coinCode}" }
                        .toList()
                )
            )
        )
    }
}