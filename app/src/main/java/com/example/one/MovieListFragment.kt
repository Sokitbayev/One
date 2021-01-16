package com.example.one
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.one.databinding.FragmentMovieListBinding


class MovieListFragment: Fragment() , MovieAdapter.OnItemClickListener{
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }
    val simpleAdapter = MovieListAdapter(this)
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_list,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        configureRecyclerView()
        return binding.root

    }


    private fun configureRecyclerView() {
        binding.rvMovie.layoutManager = LinearLayoutManager(context)
        binding.rvMovie.adapter = simpleAdapter
        viewModel.getMovieList().observe(viewLifecycleOwner,{
            it?.let{
                simpleAdapter.submitList(it)
            }
        })
    }

    override fun onItemClicked(movie: MovieData) {
        requireView().findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToSingleMovieFragment(movie))
    }


}
