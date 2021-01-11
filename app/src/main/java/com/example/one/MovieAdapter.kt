package com.example.one

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.one.retrofit.image_url
import kotlinx.android.synthetic.main.single_item.view.*
class MovieAdapter(var movies: List<Result>,  val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        return holder.bind(movies[position],itemClickListener,position)

    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.iv_movie_photo
        val title = itemView.tv_title
        val rating = itemView.tv_rating
        fun bind (movie: Result, clickListener: OnItemClickListener, position: Int)
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
        fun onItemClicked(movie: Result)
    }
}
