package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
=======
import com.example.seminar1.databinding.ActivityDetailBinding
import com.example.seminar1.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDetailName.text = intent.getStringExtra("name")
        binding.tvDetailIntroduce.text = intent.getStringExtra("introduce")


>>>>>>> Stashed changes
    }
}