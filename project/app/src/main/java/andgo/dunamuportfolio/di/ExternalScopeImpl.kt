package andgo.dunamuportfolio.di

import andgo.dunamuportfolio.DunamuApplication
import andgo.dunamuportfolio.domain.di.ExternalCoroutineScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ExternalCoroutineScopeImpl @Inject constructor(): ExternalCoroutineScope {
    override val scope: CoroutineScope get() = DunamuApplication.instance.applicationScope
}