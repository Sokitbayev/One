package com.example.one.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.one.R
import com.example.one.database.movie.MovieData
import com.example.one.database.movie.MovieDatabase
import com.example.one.databinding.FragmentMovieListBinding
import com.example.one.extensions.toast

class MovieListFragment : Fragment(), OnItemClickListener {

    private val adapter = MovieListAdapter(this)

    private lateinit var binding: FragmentMovieListBinding

    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val dataSource = MovieDatabase.getInstance(application).movieDatabaseDao

        val viewModelFactory = MovieListViewModelFactory(dataSource)

        val movieListViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        binding.movieListViewModel = movieListViewModel

        configureRecyclerView()
        binding.refreshLayout.setOnRefreshListener {
            movieListViewModel.loadMovies()
            movieListViewModel.loadQuote()
            binding.refreshLayout.isRefreshing = false
        }

        movieListViewModel.errorMessage.observe(viewLifecycleOwner,{
            showErrorMessage(it)
        })

        return binding.root
    }

    private fun configureRecyclerView() {
        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        binding.rvMovie.adapter = adapter
        movieListViewModel.movieList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun showErrorMessage(e: Exception){
        e.message?.toast(requireContext())
    }

    override fun onItemClicked(movie: MovieData) =
        requireView().findNavController()
            .navigate(MovieListFragmentDirections.actionMovieListFragmentToSingleMovieFragment(movie))
}
