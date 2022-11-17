package andgo.dunamuportfolio.domain.usecase

import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.UpbitCoinModel
import andgo.dunamuportfolio.domain.usecase.core.FlowUseCase
import andgo.dunamuportfolio.domain.usecase.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCoinPriceUseCase @Inject constructor(
    private val upbitRepository: UpbitRepository
) :
    FlowUseCase<Unit, UpbitCoinModel>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<UpbitCoinModel>> {
        return upbitRepository.response.map { Result.Success(it) }
    }
}