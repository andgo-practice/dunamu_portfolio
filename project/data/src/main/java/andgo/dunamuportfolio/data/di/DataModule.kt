package andgo.dunamuportfolio.data.di

import andgo.dunamuportfolio.data.UpbitRepositoryImpl
import andgo.dunamuportfolio.data.service.UpbitRemote
import andgo.dunamuportfolio.data.service.UpbitRemoteImpl
import andgo.dunamuportfolio.data.service.UpbitRequestAdapter
import andgo.dunamuportfolio.data.service.UpbitService
import andgo.dunamuportfolio.data.util.ScarletUpbitServiceProvider
import andgo.dunamuportfolio.domain.UpbitRepository
import android.app.Application
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @get:Binds
    val UpbitRemoteImpl.upbitService: UpbitRemote

    @get:Binds
    val UpbitRepositoryImpl.upbitRepository: UpbitRepository

    companion object {
        @[Provides Singleton]
        fun provideScarlet(
            application: Application,
            moshi: Moshi
        ): UpbitService = ScarletUpbitServiceProvider(
            application = application,
            moshi = moshi
        ).create()

        @[Provides Singleton]
        fun moshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(UpbitRequestAdapter())
            .build()
    }
}