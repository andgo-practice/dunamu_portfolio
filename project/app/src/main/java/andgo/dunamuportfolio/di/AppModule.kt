package andgo.dunamuportfolio.di

import andgo.dunamuportfolio.data.repository.UpbitRepositoryImpl
import andgo.dunamuportfolio.domain.UpbitRepository
import andgo.dunamuportfolio.domain.di.ExternalCoroutineScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {
    @get:Binds
    val UpbitRepositoryImpl.upbitRepository: UpbitRepository

    @get:[Binds Singleton]
    val ExternalCoroutineScopeImpl.externalScope: ExternalCoroutineScope
}