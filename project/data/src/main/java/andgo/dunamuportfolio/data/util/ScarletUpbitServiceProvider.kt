package andgo.dunamuportfolio.data.util

import andgo.dunamuportfolio.data.BuildConfig
import andgo.dunamuportfolio.data.service.UpbitService
import android.app.Application
import com.squareup.moshi.Moshi
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal class ScarletUpbitServiceProvider(
    private val application: Application,
    private val moshi: Moshi
) {
    private val scarlet: Scarlet by lazy {
        Scarlet.Builder()
            .webSocketFactory(client().newWebSocketFactory(BuildConfig.SOCKET_ADDRESS))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory(moshi))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory())
            .lifecycle(AndroidLifecycle.ofApplicationForeground(application))
            .build()
    }

    private fun client(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
                else HttpLoggingInterceptor.Level.NONE
            )

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    fun create(): UpbitService = scarlet.create()
}