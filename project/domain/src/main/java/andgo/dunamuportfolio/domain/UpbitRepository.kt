package andgo.dunamuportfolio.domain

import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.usecase.CoinSubscribeParam
import kotlinx.coroutines.flow.Flow

interface UpbitRepository {
    val response: Flow<UpbitCoinModel>
    val event: Flow<String>
    fun subscribe(coinSubscribeParam: CoinSubscribeParam)
    fun unSubscribe()
}