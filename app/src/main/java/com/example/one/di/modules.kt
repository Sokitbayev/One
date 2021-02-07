package com.example.one.di

import com.example.one.movielist.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {MovieListViewModel()}
}

