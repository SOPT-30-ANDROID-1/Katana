package com.example.seminar1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.data.ServiceCreator
import com.example.seminar1.data.sopt.RequestSignIn
import com.example.seminar1.data.sopt.ResponseSignIn
import com.example.seminar1.data.sopt.ResponseWrapper
import com.example.seminar1.databinding.ActivitySignInBinding
import com.example.seminar1.ui.home.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
        initAuthButtonClickListeners()

    }

    private fun initAuthButtonClickListeners() {
        //회원가입 버튼 클릭 시
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
        //회원가입 시 데이터 받아오기
        private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> if(result.resultCode == Activity.RESULT_OK){
            result.data.let {
                val userId = it?.getStringExtra("id")
                val userPw = it?.getStringExtra("pw")
                binding.etId.setText(userId)
                binding.etPw.setText(userPw)
            }
        }
    }

    private fun initEvent(){
        binding.btnLogin.setOnClickListener{
            loginNetwork()
        }
    }

    private fun loginNetwork(){
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call : Call<ResponseWrapper<ResponseSignIn>> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignIn>> {
            override fun onResponse(
                    call : Call<ResponseWrapper<ResponseSignIn>>,
                    response: Response<ResponseWrapper<ResponseSignIn>>
            ){
                if (response.isSuccessful){
                    val data = response.body()?.data

                    Toast.makeText(this@SignInActivity,"${data?.email}님 반갑습니다!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                }else Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseSignIn>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }
}
