package com.example.seminar1.ui.home.profile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.seminar1.databinding.ActivitySettingBinding
import com.example.seminar1.util.SOPTSharedPreferences

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener{
            SOPTSharedPreferences.setLogout(this)
            showToast("자동로그인이 해제되었습니다.")
        }
    }

    fun Context.showToast(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}