package com.example.one.retrofit.quote

import retrofit2.Response
import retrofit2.http.GET

interface QuoteEndpoints {
    @GET("/api/1.0/?method=getQuote&format=json")
    suspend fun getQuote(): Response<Quote>
}