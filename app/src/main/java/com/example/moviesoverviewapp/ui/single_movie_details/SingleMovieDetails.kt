package com.example.moviesoverviewapp.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviesoverviewapp.data.api.POSTER_BASE_URL
import com.example.moviesoverviewapp.data.api.TMDBClient
import com.example.moviesoverviewapp.data.api.TMDBInterface
import com.example.moviesoverviewapp.data.repository.NetworkState
import com.example.moviesoverviewapp.data.vo.MovieDetails
import com.example.moviesoverviewapp.databinding.ActivitySingleMovieDetailsBinding
import java.text.NumberFormat
import java.util.*

class SingleMovieDetails : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieDetailsRepository: MovieDetailsRepository

    private lateinit var binding: ActivitySingleMovieDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId: Int = intent.getIntExtra("id",1)

        val apiService : TMDBInterface = TMDBClient.getClient()
        movieDetailsRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this,{
            binding.pbLoading.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.tvError.visibility = if(it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    private fun bindUI(it:MovieDetails){

        binding.tvMovieTitle.text = it.title
        binding.tvMovieTagLine.text = it.tagline
        binding.tvMovieRD.text = it.releaseDate
        binding.tvMovieRating.text = it.rating.toString()
        binding.tvMovieRuntime.text = it.runtime.toString() + " minutes"
        binding.tvMovieOverview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)

        binding.tvMovieBudget.text = formatCurrency.format(it.budget)
        binding.tvMovieRevenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL: String = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(binding.ivMoviePoster)
    }

    private  fun getViewModel(movieId:Int): SingleMovieViewModel{
      return ViewModelProviders.of(this, object : ViewModelProvider.Factory{
          override fun <T : ViewModel?> create(modelClass: Class<T>): T {
              return SingleMovieViewModel(movieDetailsRepository,movieId) as T
          }
      })[SingleMovieViewModel::class.java]
    }
}