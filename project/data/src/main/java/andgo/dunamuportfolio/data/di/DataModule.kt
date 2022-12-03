package andgo.dunamuportfolio.data.di

import andgo.dunamuportfolio.data.BuildConfig
import andgo.dunamuportfolio.data.remote.UpbitRemote
import andgo.dunamuportfolio.data.remote.UpbitRemoteImpl
import andgo.dunamuportfolio.data.remote.service.UpbitRequestAdapter
import andgo.dunamuportfolio.data.remote.service.UpbitService
import andgo.dunamuportfolio.data.remote.service.UpbitServiceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @get:[Binds Singleton]
    val UpbitRemoteImpl.upbitRemote: UpbitRemote

    @get:[Binds Singleton]
    val UpbitServiceImpl.upbitService: UpbitService

    companion object {
        @[Provides Singleton]
        fun moshi(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(UpbitRequestAdapter())
            .build()

        @[Provides Singleton]
        fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                    else HttpLoggingInterceptor.Level.NONE
                )
            ).build()
    }
}