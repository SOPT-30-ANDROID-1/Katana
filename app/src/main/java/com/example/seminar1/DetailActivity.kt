package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDetailName.text = intent.getStringExtra("name")
        binding.tvDetailIntroduce.text = intent.getStringExtra("introduce")


    }
}