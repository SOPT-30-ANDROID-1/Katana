package com.example.seminar1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.seminar1.data.ServiceCreator
import com.example.seminar1.data.sopt.RequestSignUp
import com.example.seminar1.data.sopt.ResponseSignUp
import com.example.seminar1.data.sopt.ResponseWrapper
import com.example.seminar1.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()

    }

    private fun initEvent(){
        binding.btnSignupDone.setOnClickListener{
            signUpNetwork()
            val intent = Intent(this, SignInActivity::class.java).apply {
                putExtra("id",binding.etSignupId.text.toString())
                putExtra("pw",binding.etSignupPw.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun signUpNetwork(){
        val requestSignUp = RequestSignUp(
                id = binding.etSignupId.text.toString(),
                name = binding.etSignupName.text.toString(),
                password = binding.etSignupPw.text.toString()
        )

        val call : Call<ResponseWrapper<ResponseSignUp>> = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignUp>> {
            override fun onResponse(
                    call : Call<ResponseWrapper<ResponseSignUp>>,
                    response: Response<ResponseWrapper<ResponseSignUp>>
            ){
                if (response.isSuccessful){
                    val data = response.body()?.data

                    Toast.makeText(this@SignUpActivity,"${data?.id}님 회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }else Toast.makeText(this@SignUpActivity,"회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseWrapper<ResponseSignUp>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }
}