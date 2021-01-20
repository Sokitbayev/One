package com.example.one.movielist

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.one.database.movie.MovieData
import com.example.one.database.movie.MovieDatabaseDao
import com.example.one.database.movie.PopularMovies
import com.example.one.retrofit.movies.TmdbEndpoints
import com.example.one.retrofit.movies.TmdbServiceBuilder
import com.example.one.retrofit.movies.api_key
import com.example.one.retrofit.quote.QuoteEndpoints
import com.example.one.retrofit.quote.QuoteServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
        val database: MovieDatabaseDao,
        val application: Application): 
        ViewModel() {
    
    private var _movieList : MutableLiveData<List<MovieData>> = MutableLiveData()
    val movieList: LiveData<List<MovieData>>
        get() =_movieList

    private var _quoteText : MutableLiveData<String> = MutableLiveData("Reload")
    val quote: LiveData<String>
        get() = _quoteText

    init {
        loadMoviesFromTmdb()
        loadQuote()
    }

    fun loadMoviesFromTmdb() {
        val request = TmdbServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(api_key)

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                    _movieList.value = response.body()!!.results
                    }
                }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {

                Toast.makeText(application.applicationContext, t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun loadQuote() {
        val request = QuoteServiceBuilder.buildService(QuoteEndpoints::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.getQuote()
                if (response.isSuccessful && response.body() != null) {
                    _quoteText.value = response.body()!!.quoteText
                }
            } catch (e: Exception) {
                Toast.makeText(application.applicationContext,
                        "Error: ${e.message}",
                        Toast.LENGTH_LONG).show()
            }
        }
    }}