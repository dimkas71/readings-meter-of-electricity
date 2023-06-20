package ua.dimkas71.rest

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Credentials
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ua.dimkas71.common.PASSWORD_KEY
import ua.dimkas71.common.URI_KEY
import ua.dimkas71.common.USER_KEY
import ua.dimkas71.common.getSharedPreferences
import ua.dimkas71.data.SummaryConsumingBySectors

interface CounterServiceApi {

    @GET("consuming")
    suspend fun getSummaryConsuming(@Query("period") period: String): List<SummaryConsumingBySectors>

    companion object {

        fun service(context: Context): CounterServiceApi {
            val prefManager = getSharedPreferences(context)

            val baseUri = prefManager.getString(URI_KEY, "http://194.44.128.108:9090/demo/hs/")
            val user = prefManager.getString(USER_KEY, "service")
            val password = prefManager.getString(PASSWORD_KEY, "")

            val client = OkHttpClient.Builder()
                .addInterceptor {
                    val credentials = Credentials.basic(user, password)
                    val requestWithAuth =
                        it.request().newBuilder().header("Authorization", credentials).build()
                    it?.proceed(requestWithAuth)!!
                }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUri)
                .client(client)
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .build()

            return retrofit.create(CounterServiceApi::class.java)
        }

    }
}