package com.example.one.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.one.database.movie.MovieData
import com.example.one.database.movie.MovieDatabaseDao
import com.example.one.retrofit.movies.TmdbEndpoints
import com.example.one.retrofit.movies.TmdbServiceBuilder
import com.example.one.retrofit.movies.api_key
import com.example.one.retrofit.quote.QuoteEndpoints
import com.example.one.retrofit.quote.QuoteServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(
    val database: MovieDatabaseDao
) :
    ViewModel() {

    private var _movieList: MutableLiveData<List<MovieData>> = MutableLiveData()
    val movieList: LiveData<List<MovieData>>
        get() = _movieList

    private var _quoteText: MutableLiveData<String> = MutableLiveData("Reload")
    val quote: LiveData<String>
        get() = _quoteText

    private var _errorMessage: MutableLiveData<Exception> = MutableLiveData()
    val errorMessage: LiveData<Exception>
        get() = _errorMessage

    init {
        loadMovies()
        loadQuote()
    }

    fun loadMovies() {
        val request = TmdbServiceBuilder.buildService(TmdbEndpoints::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.getMovies(api_key)
                if (response.isSuccessful) {
                    _movieList.value = response.body()!!.results
                }
            } catch (e: Exception) {
                _errorMessage.value = e
            }
        }
    }

    fun loadQuote() {
        val request = QuoteServiceBuilder.buildService(QuoteEndpoints::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = request.getQuote()
                if (response.isSuccessful) {
                    _quoteText.value = response.body()!!.quoteText
                }
            } catch (e: Exception) {
                _errorMessage.value = e
            }
        }
    }
}