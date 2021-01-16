package com.example.one

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.one.retrofit.movies.ServiceBuilder
import com.example.one.retrofit.movies.TmdbEndpoints
import com.example.one.retrofit.movies.api_key
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel: ViewModel() {
    var movieList : MutableLiveData<List<MovieData>> = MutableLiveData()

    init {
        loadMoviesFromTmdb()
    }
    @JvmName("getMovieList1")
    fun getMovieList() = movieList


    private fun loadMoviesFromTmdb() {
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(api_key)

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                        movieList.value = response.body()!!.results
                    }
                }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                TODO()
            }


        })
    }
}