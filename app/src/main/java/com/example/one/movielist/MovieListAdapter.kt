package com.example.one.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.one.R
import com.example.one.database.movie.MovieData
import com.example.one.retrofit.movies.image_url
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(private val itemClickListener: MovieListFragment) :
    ListAdapter<MovieData, MovieListAdapter.MoviesViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(currentList[position], itemClickListener)
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.iv_movie_photo
        private val title: TextView = itemView.movie_title
        private val rating: TextView = itemView.movie_rating
        fun bind(movie: MovieData, clickListener: MovieListFragment) {
            Glide.with(itemView.context).load(image_url + movie.poster_path).into(photo)
            title.text = movie.title
            rating.text = movie.vote_average.toString()
            itemView.setOnClickListener {
                clickListener.onItemClicked(movie)
            }

        }
    }
}

class MovieDiffUtil : DiffUtil.ItemCallback<MovieData>() {

    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}

interface OnItemClickListener {
    fun onItemClicked(movie: MovieData)
}