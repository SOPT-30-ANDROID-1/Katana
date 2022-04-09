package com.example.seminar1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.seminar1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ClickSignUpDone()

    }
    //회원가입 완료 버튼 클릭시
    private fun ClickSignUpDone(){
        binding.btnSignupDone.setOnClickListener {
            val name = binding.etSignupName.text.toString()
            val id = binding.etSignupId.text.toString()
            val pw = binding.etSignupPw.text.toString()

            //name,id,pw 셋 중 하나라도 비어있다면
            if(name.isEmpty() || id.isEmpty() || pw.isEmpty()){
                Toast.makeText(this,"입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()

            }else{ //모두 채워져 있을 경우
                val intent = Intent(this, SignInActivity::class.java).apply {
                    putExtra("id",id)
                    putExtra("pw",pw)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}