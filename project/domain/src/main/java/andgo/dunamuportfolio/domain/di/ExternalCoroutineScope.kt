package andgo.dunamuportfolio.domain.di

import kotlinx.coroutines.CoroutineScope

interface ExternalCoroutineScope {
    val scope: CoroutineScope
}