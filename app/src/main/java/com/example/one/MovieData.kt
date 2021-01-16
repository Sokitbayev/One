package com.example.one
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PopularMovies(
    val results: List<MovieData>
)
@Parcelize
data class MovieData(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
): Parcelable