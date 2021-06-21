package com.example.moviesoverviewapp.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.moviesoverviewapp.data.api.TMDBInterface
import com.example.moviesoverviewapp.data.repository.MovieDetailsNetworkDS
import com.example.moviesoverviewapp.data.repository.NetworkState
import com.example.moviesoverviewapp.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TMDBInterface) {

    lateinit var movieDetailsNetworkDS: MovieDetailsNetworkDS

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId:Int): LiveData<MovieDetails>{
        movieDetailsNetworkDS = MovieDetailsNetworkDS(apiService,compositeDisposable)
        movieDetailsNetworkDS.fetchMovieDetails(movieId)

        return movieDetailsNetworkDS.downloadedMovieDetails
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState>{
        return movieDetailsNetworkDS.networkState
    }
}