package com.example.moviesoverviewapp.data.api

import com.example.moviesoverviewapp.data.vo.MovieDetails
import com.example.moviesoverviewapp.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBInterface {

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId")id: Int): Single<MovieDetails>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") pageNumber: Int): Single<MovieResponse>
}