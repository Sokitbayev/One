package com.example.one.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.one.R
import com.example.one.database.movie.MovieData

class MovieListAdapter(private val itemClickListener: MovieListFragment) : ListAdapter<MovieData, MovieAdapter.MoviesViewHolder>(SimpleDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MoviesViewHolder {
        return MovieAdapter.MoviesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.MoviesViewHolder, position: Int) {
        holder.bind(currentList[position], itemClickListener, position)
    }

}
    class SimpleDiffUtil: DiffUtil.ItemCallback<MovieData>() {

        override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
            return oldItem == newItem
        }
}