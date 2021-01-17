package com.example.one.database.movie

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDatabaseDao{
    @Insert
    fun insertMovie(movie: MovieData)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieData>>

    @Query("SELECT * FROM movie_table ORDER BY id DESC LIMIT 1")
    fun getMovie(): MovieData

    @Query("DELETE FROM movie_table")
    fun clear()
}