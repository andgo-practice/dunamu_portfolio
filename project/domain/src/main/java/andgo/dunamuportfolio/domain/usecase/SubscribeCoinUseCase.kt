package andgo.dunamuportfolio.domain.usecase

import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.model.CoinPriceUnit
import andgo.dunamuportfolio.domain.model.CoinType
import andgo.dunamuportfolio.domain.usecase.core.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SubscribeCoinUseCase @Inject constructor(
    private val upbitRepository: UpbitRepository
): UseCase<CoinSubscribeParam, Unit>(Dispatchers.IO) {
    override suspend fun execute(parameters: CoinSubscribeParam) {
        upbitRepository.subscribe(parameters)
    }
}

data class CoinSubscribeParam(
    val coinTypeParams: List<CoinType>,
    val coinPriceUnit: CoinPriceUnit
)