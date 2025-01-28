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
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(GetWorkApi::class.java)



interface GetWorkApi {
    @GET("u/0/uc")
    suspend fun getWorkApi(
        @Query("id") id: String,
        @Query("export") export: String
    ): Work
}

