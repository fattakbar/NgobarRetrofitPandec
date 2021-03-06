package com.zendev.ngobarretrofit.api

import com.zendev.ngobarretrofit.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}