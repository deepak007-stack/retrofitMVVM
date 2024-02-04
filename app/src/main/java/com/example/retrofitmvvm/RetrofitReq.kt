package com.example.retrofitmvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitReq {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : ApiService = retrofit.create(ApiService::class.java)
}