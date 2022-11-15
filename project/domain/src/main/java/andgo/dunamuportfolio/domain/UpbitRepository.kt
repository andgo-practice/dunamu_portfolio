package andgo.dunamuportfolio.domain

import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import kotlinx.coroutines.flow.Flow

interface UpbitRepository {
    val response: Flow<UpbitCoinModel>
    val event: Flow<String>
    fun subscribe()
    fun unSubscribe()
}