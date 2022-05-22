package com.example.seminar1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.seminar1.adapter.ViewPagerAdapter
import com.example.seminar1.databinding.ActivityHomeBinding
import com.example.seminar1.fragment.CameraFragment
import com.example.seminar1.fragment.HomeFragment
import com.example.seminar1.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()

    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = viewPagerAdapter
    }

    private fun initBottomNavi(){
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.vpMain.currentItem = PROFILE_FRAGMENT
                    true
                }
                R.id.menu_home -> {
                    binding.vpMain.currentItem = HOME_FRAGMENT
                    true
                }
                else -> {
                    binding.vpMain.currentItem = PHOTO_FRAGMENT
                    true
                }
            }
        }
    }

    companion object {
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val PHOTO_FRAGMENT = 2
    }
}

