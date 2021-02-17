package com.example.one.singlemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.one.R
import com.example.one.R.layout.fragment_single_movie
import com.example.one.retrofit.movies.image_url
import kotlinx.android.synthetic.main.fragment_single_movie.*

class SingleMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_single_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = SingleMovieFragmentArgs.fromBundle(requireArguments()).movie
        movie_title.text = movie.title
        movie_rating.text = movie.vote_average.toString()
        movie_overview.text = movie.overview
        Glide.with(view.context).load(image_url + movie.poster_path)
            .into(view.findViewById(R.id.movie_photo))
    }
}