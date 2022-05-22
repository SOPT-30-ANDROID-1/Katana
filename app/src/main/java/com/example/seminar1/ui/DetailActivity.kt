package com.example.seminar1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.seminar1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            tvDetailName.text = intent.getStringExtra("name")
            tvDetailIntroduce.text = intent.getStringExtra("introduce")
            Glide.with(binding.root)
                    .load(intent.getStringExtra("profile"))
                    .circleCrop()
                    .into(binding.ivProfile2)
        }
    }
}