package andgo.dunamuportfolio.domain.di

import kotlinx.coroutines.CoroutineDispatcher

interface AsyncDispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}