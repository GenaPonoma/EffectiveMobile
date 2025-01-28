package com.example.effectivemobile.retrofit

import com.example.effectivemobile.data.model.Work
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://drive.usercontent.google.com/"

val retrofit: GetWorkApi = Retrofit.Builder()
    .client(createOkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(GetWorkApi::class.java)

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS) // Тайм-аут подключения увеличен до 60 секунд
        .readTimeout(100, TimeUnit.SECONDS) // Тайм-аут чтения ответа увеличен до 60 секунд
        .writeTimeout(15, TimeUnit.SECONDS) // Тайм-аут записи увеличен до 15 секунд
        .build()
}

interface GetWorkApi {
    @GET("u/0/uc")
    suspend fun getWorkApi(
        @Query("id") id: String,
        @Query("export") export: String
    ): Work
}

