package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.data.model.UpbitCoinRemoteModel
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

internal interface UpbitService {

    @get:Receive
    val events: Flow<WebSocket.Event>

    @get:Receive
    val upbitPriceInfo: Flow<UpbitCoinRemoteModel>

    @Send
    fun subscribe(params: List<Any>)
}