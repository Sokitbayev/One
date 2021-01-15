package com.example.one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class MovieListAdapter(val itemClickListener: MovieAdapter.OnItemClickListener) : ListAdapter<Result, MovieAdapter.MoviesViewHolder>(SimpleDiffUtil()) {

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
    class SimpleDiffUtil: DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
}