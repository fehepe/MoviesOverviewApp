package com.example.moviesoverviewapp.data.api

import com.example.moviesoverviewapp.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBInterface {

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId")id: Int): Single<MovieDetails>
}