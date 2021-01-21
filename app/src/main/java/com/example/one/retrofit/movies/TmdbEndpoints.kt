package com.example.one.retrofit.movies
import com.example.one.database.movie.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {
    @GET("/3/movie/upcoming")
    suspend fun getMovies(@Query("api_key") key: String): Response<PopularMovies>
}