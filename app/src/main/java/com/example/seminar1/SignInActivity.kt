package com.example.seminar1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.databinding.ActivitySignInBinding
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
        /*
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val pw = binding.etPw.text.toString()

            //id,pw 둘 중 하나라도 비어있다면
            if (id.isBlank() || pw.isBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()

            } else { //모두 채워져 있을 경우
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
     */
        //회원가입 버튼 클릭 시
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            //resultLauncher.launch(intent)
            startActivity(intent)
        }
    }
    /*
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
     */

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
