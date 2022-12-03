package andgo.dunamuportfolio.di

import andgo.dunamuportfolio.DunamuApplication
import andgo.dunamuportfolio.data.repository.UpbitRepositoryImpl
import andgo.dunamuportfolio.domain.UpbitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {
    @get:Binds
    val UpbitRepositoryImpl.upbitRepository: UpbitRepository

    companion object {
        @[Provides Singleton]
        fun provideAppCoroutineScope() = DunamuApplication.instance.applicationScope
    }
}