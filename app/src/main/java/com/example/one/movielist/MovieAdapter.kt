package com.example.one.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.one.R
import com.example.one.database.movie.MovieData
import com.example.one.retrofit.movies.image_url
import kotlinx.android.synthetic.main.movie_item.view.*
class MovieAdapter(var movies: List<MovieData>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        return holder.bind(movies[position],itemClickListener,position)

    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.iv_movie_photo
        private val title: TextView = itemView.movie_title
        private val rating: TextView = itemView.movie_rating
        fun bind (movie: MovieData, clickListener: OnItemClickListener, position: Int)
        {
            Glide.with(itemView.context).load(image_url + movie.poster_path).into(photo)
            title.text = movie.title
            rating.text = movie.vote_average.toString()
            itemView.setOnClickListener {
                clickListener.onItemClicked(movie)
            }

        }
    }

    interface OnItemClickListener{
        fun onItemClicked(movie: MovieData)
    }
}
