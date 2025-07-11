package com.example.recyclerview.data.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ApiService


object RetrofitClient {
    private const val BASE_URL = "https://assignment-git-main-liberator79s-projects.vercel.app/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
