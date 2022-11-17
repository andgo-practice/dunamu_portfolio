package andgo.dunamuportfolio.data.service

import andgo.dunamuportfolio.data.mapper.WebSocketEventMapper
import andgo.dunamuportfolio.data.model.UpbitCoinRemoteModel
import andgo.dunamuportfolio.data.model.WebSocketEvent
import app.cash.turbine.test
import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UpbitServiceTest {
    @Test
    fun `when events are requested then the correct data is returned`() = runTest {
        // Given
        val eventMapper = mock<WebSocketEventMapper> {
            on {
                toSocketEvent(
                    event = WebSocket.Event.OnMessageReceived(
                        message = Message.Text(value = "Stocks")
                    )
                )
            } doReturn WebSocketEvent.MessageReceived

            on {
                toSocketEvent(
                    event = WebSocket.Event.OnMessageReceived(
                        message = Message.Text(value = "Microsoft")
                    )
                )
            } doReturn WebSocketEvent.MessageReceived
        }

        val scarletStockService = mock<UpbitService> {
            on { events } doReturn flowOf(
                WebSocket.Event.OnMessageReceived(
                    message = Message.Text(value = "Stocks")
                ),
                WebSocket.Event.OnMessageReceived(
                    message = Message.Text(value = "Microsoft")
                )
            )
        }

        val stockRemote = createStockRemote(
            upbitService = scarletStockService,
            eventMapper = eventMapper
        )

        // when
        val events = stockRemote.events

        // then
        events.test {
            assertEquals(
                WebSocketEvent.MessageReceived, awaitItem()
            )
            assertEquals(
                WebSocketEvent.MessageReceived, awaitItem()
            )
            awaitComplete()
        }
    }

    @Test
    fun `when stock info is requested then the correct data is returned`() = runTest {
        // Given
        val scarletStockService = mock<UpbitService> {
            on { coinResponse } doReturn flowOf(
                UpbitCoinRemoteModel(price = 8999.22),
            )
        }

        val stockRemote = createStockRemote(upbitService = scarletStockService)

        // when
        val upbitModel = stockRemote.upbitModel

        // then
        upbitModel.test {
            assertEquals(UpbitCoinRemoteModel(price = 8999.22), awaitItem())
            awaitComplete()
        }
    }

    private fun createStockRemote(
        upbitService: UpbitService = mock(),
        eventMapper: WebSocketEventMapper = mock()
    ) = UpbitRemoteImpl(
        upbitService = upbitService,
        eventMapper = eventMapper
    )
}