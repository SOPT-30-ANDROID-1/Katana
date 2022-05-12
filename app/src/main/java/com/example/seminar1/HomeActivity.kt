package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seminar1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()

    }

    private fun initTransactionEvent(){
        val fragment1 = FollowerRecyclerView()
        val fragment2 = RepositoryRecyclerView()

        supportFragmentManager.beginTransaction().add(R.id.fragment_list, fragment1).commit()

        binding.btnFollower.setOnClickListener {
            if (position == REPO_POSITION) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_list, fragment1).commit()
                position = FOLLOWER_POSITION
            }
        }

        binding.btnRepo.setOnClickListener {
            if (position == FOLLOWER_POSITION){
                supportFragmentManager.beginTransaction().replace(R.id.fragment_list,fragment2).commit()
                position = REPO_POSITION
            }
        }
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}

