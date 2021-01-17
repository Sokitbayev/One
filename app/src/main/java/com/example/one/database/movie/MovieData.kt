package com.example.one.database.movie
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class PopularMovies(
    val results: List<MovieData>
)

@Entity(tableName = "movie_table")
@Parcelize
data class MovieData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int
): Parcelable
