package kg.lugatdictionary.data.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kg.lugatdictionary.data.memory.LugatSharedPreferences
import java.util.concurrent.TimeUnit

fun <T> provideNetworkService(
    clazz: Class<T>,
    sharedPref: LugatSharedPreferences
): T = getRetrofit(sharedPref).create(clazz)

@Suppress("MayBeConstant")
private val currentUrl = TEST_URL

private var retrofit: Retrofit? = null

private fun getRetrofit(
    sharedPref: LugatSharedPreferences
) = retrofit ?: createRetrofit(currentUrl, sharedPref).also {
    retrofit = it
}

@Suppress("SameParameterValue")
private fun createRetrofit(
    baseUrl: String,
    sharedPref: LugatSharedPreferences
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(getOkHttpClient(sharedPref))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun getOkHttpClient(
    sharedPref: LugatSharedPreferences
): OkHttpClient = OkHttpClient.Builder()
    .setTimeout()
    .addInterceptor(getLoggingInterceptor())
    .build()


private fun OkHttpClient.Builder.setTimeout() = apply {
    readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
}

private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}