package com.example.one
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.one.retrofit.ServiceBuilder
import com.example.one.retrofit.api_key
import com.example.smth.retrofit.TmdbEndpoints
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.movie_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListFragment: Fragment() , MovieAdapter.OnItemClickListener{
    val simpleAdapter = MovieListAdapter(this@MovieListFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMoviesFromTMDB()

        refreshLayout.setOnRefreshListener {
            getMoviesFromTMDB()
            refreshLayout.isRefreshing = false
        }
    }

    private fun getMoviesFromTMDB() {
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(api_key)

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                    rv_movie.adapter =
                        MovieAdapter(response.body()!!.results, this@MovieListFragment)
                    rv_movie.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)

                        adapter = simpleAdapter
                        simpleAdapter.submitList(response.body()!!.results)


                    }
                }
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClicked(movie: Result) {
        requireView().findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToSingleMovieFragment(movie))
    }



}
