package dependencies.android

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import dependencies.android.Constant.BASE_URL
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun client(context: Context): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor() {
                    Log.e("InetLog", it)
                    print("Log -> $it")
                })
                .addInterceptor {
                    try {
                        it.proceed(it.request())
                    } catch (e: Exception) {
                        Response.Builder().code(-2).body(
                            JsonObject().apply {
                                addProperty("error", "failure_client")
                            }.asString.toResponseBody("application/json".toMediaType())
                        ).build()
                    }
                }
                .build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }
}