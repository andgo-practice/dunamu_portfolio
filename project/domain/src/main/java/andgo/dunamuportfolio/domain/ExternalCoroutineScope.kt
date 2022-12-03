package andgo.dunamuportfolio.domain

import kotlinx.coroutines.CoroutineScope

interface ExternalCoroutineScope {
    val scope: CoroutineScope
}