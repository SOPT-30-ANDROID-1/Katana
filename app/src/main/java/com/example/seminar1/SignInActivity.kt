package com.example.seminar1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        ClickSignIn()
        ClickSignUp()

    }
    //로그인 버튼 클릭 시
    private fun ClickSignIn(){
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPw.text.toString()

            //id,pw 둘 중 하나라도 비어있다면
            if(id.isEmpty() || pw.isEmpty()){
                Toast.makeText(this,"아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()

            }else{ //모두 채워져 있을 경우
                Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
    //회원가입 시 데이터 받아오기
    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> if(result.resultCode == Activity.RESULT_OK){
            result.data.let {
                val userId = it?.getStringExtra("id")
                val userPw = it?.getStringExtra("pw")
                binding.etId.setText(userId)
                binding.etPw.setText(userPw)
            }
          }
        }
    }
    //회원가입 버튼 클릭 시
    private fun ClickSignUp(){
        binding.btnSignup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}