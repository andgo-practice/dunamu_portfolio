package andgo.dunamuportfolio.domain.usecase

import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.UpbitWebSocketEvent
import andgo.dunamuportfolio.domain.usecase.core.FlowUseCase
import andgo.dunamuportfolio.domain.usecase.core.Result
import andgo.dunamuportfolio.domain.usecase.core.UseCase
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DisConnectWebSocketUseCase @Inject constructor(
    private val upbitRepository: UpbitRepository
) : UseCase<Unit, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: Unit) {
        upbitRepository.disconnect()
    }
}
