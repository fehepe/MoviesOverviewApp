package com.example.moviesoverviewapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesoverviewapp.databinding.ActivityMainBinding
import com.example.moviesoverviewapp.ui.single_movie_details.SingleMovieDetails

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener{
            val intent = Intent(this,SingleMovieDetails::class.java)
            intent.putExtra("id",578701)
            this.startActivity(intent)
        }

    }
}