package andgo.dunamuportfolio.data.service

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class UpbitRequestAdapter {
    @ToJson
    fun toJson(writer: JsonWriter,
               data: List<Any>,
               delegate: JsonAdapter<List<Any>>
    ) {
        val wasSerializeNulls: Boolean = writer.serializeNulls
        writer.serializeNulls = true
        try {
            Log.d("test","data:${delegate.toJson(data)}")
            delegate.toJson(writer, data)
        } finally {
            writer.isLenient = wasSerializeNulls
        }
    }
}