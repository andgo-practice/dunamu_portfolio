package andgo.dunamuportfolio.data

import andgo.dunamuportfolio.data.mapper.toUpbitCoinModel
import andgo.dunamuportfolio.data.model.SubscribeData
import andgo.dunamuportfolio.data.model.Ticket
import andgo.dunamuportfolio.data.model.Type
import andgo.dunamuportfolio.data.service.*
import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import java.util.*
import javax.inject.Inject

class UpbitRepositoryImpl @Inject constructor(
    private val upbitRemote: UpbitRemote
) : UpbitRepository {

    override val event: Flow<String>
        get() = upbitRemote.events.map { it.toString() }

    override val response: Flow<UpbitCoinModel> =
        upbitRemote.upbitModel.mapNotNull { it.toUpbitCoinModel() }

    override fun subscribe(coinSubscribeParam: CoinSubscribeParam) {
        upbitRemote.subscribe(
            SubscribeData.create(
                ticket = Ticket(ticket = UUID.randomUUID().toString()),
                type = Type(
                    type = SubscribeRequestType.Ticker.value,
                    codes = coinSubscribeParam.coinTypeParams
                        .map { it.toCoinTypeRemote() }
                        .toRequestParams(coinSubscribeParam.coinPriceUnit.toCoinUnitRemote())
                )
            )
        )
    }

    override fun unSubscribe() {
        TODO("Not yet implemented")
    }
}