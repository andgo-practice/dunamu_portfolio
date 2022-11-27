package andgo.dunamuportfolio.data.di

import andgo.dunamuportfolio.data.repository.UpbitRepositoryImpl
import andgo.dunamuportfolio.data.remote.UpbitWebServiceProvider
import andgo.dunamuportfolio.data.remote.UpbitRemote
import andgo.dunamuportfolio.data.remote.UpbitRemoteImpl
import andgo.dunamuportfolio.data.remote.service.UpbitRequestAdapter
import andgo.dunamuportfolio.data.remote.service.UpbitService
import andgo.dunamuportfolio.data.remote.service.UpbitServiceImpl
import andgo.dunamuportfolio.data.remote.service.websocket.UpbitWebSocketHandler
import andgo.dunamuportfolio.domain.UpbitRepository
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @get:Binds
    val UpbitRemoteImpl.upbitService: UpbitRemote

    @get:Binds
    val UpbitRepositoryImpl.upbitRepository: UpbitRepository

    @get:[Binds Singleton]
    val DefaultAsyncDispatcher.dispatcher: AsyncDispatcher

    companion object {
        @[Provides Singleton]
        fun provideUpbitImpl(
            upbitOkHttpClientProvider: UpbitWebServiceProvider,
            moshi: Moshi
        ): UpbitService = UpbitServiceImpl(
            UpbitWebSocketHandler(
                moshi,
                upbitOkHttpClientProvider.client(),
                upbitOkHttpClientProvider.webSocketHandler(moshi, externalScope())
            )
        )

        @[Provides Singleton]
        fun externalScope(): CoroutineScope {
            return CoroutineScope(SupervisorJob() + Dispatchers.Default)
        }

        @[Provides Singleton]
        fun moshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(UpbitRequestAdapter())
            .build()
    }
}