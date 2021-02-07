package com.example.one.retrofit.quote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteServiceBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.forismatic.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}