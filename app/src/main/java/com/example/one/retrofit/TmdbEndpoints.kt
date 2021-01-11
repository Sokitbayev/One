package com.example.smth.retrofit
import com.example.one.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {

    @GET("/3/movie/upcoming")
    fun getMovies(@Query("api_key") key: String): Call<PopularMovies>

}