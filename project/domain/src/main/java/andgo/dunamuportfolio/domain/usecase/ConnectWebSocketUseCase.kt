package andgo.dunamuportfolio.domain.usecase

import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.UpbitWebSocketEvent
import andgo.dunamuportfolio.domain.usecase.core.FlowUseCase
import andgo.dunamuportfolio.domain.usecase.core.Result
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConnectWebSocketUseCase @Inject constructor(
    private val upbitRepository: UpbitRepository
) : FlowUseCase<Unit, UpbitWebSocketEvent>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<UpbitWebSocketEvent>> {
        return upbitRepository.connect().map { Result.Success(it) }
    }
}
