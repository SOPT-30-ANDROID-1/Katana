package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
import com.example.seminar1.databinding.ActivityDetailBinding
>>>>>>> Stashed changes

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        setContentView(R.layout.activity_detail)
=======
import com.example.seminar1.databinding.ActivityDetailBinding
import com.example.seminar1.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
=======
>>>>>>> Stashed changes
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDetailName.text = intent.getStringExtra("name")
        binding.tvDetailIntroduce.text = intent.getStringExtra("introduce")


<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}