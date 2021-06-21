package com.example.moviesoverviewapp.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviesoverviewapp.data.api.TMDBInterface
import com.example.moviesoverviewapp.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : TMDBInterface, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}