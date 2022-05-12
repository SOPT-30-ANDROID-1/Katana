package com.example.seminar1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        //initAuthButtonClickListeners()
        initEvent()

    }

    private fun initEvent(){
        binding.btnSignupDone.setOnClickListener{
            signUpNetwork()
        }
    }

    private fun signUpNetwork(){
        val requestSignUp = RequestSignUp(
                id = binding.etSignupId.text.toString(),
                name = binding.etSignupName.text.toString(),
                password = binding.etSignupPw.text.toString()
        )

        val call : Call<ResponseSignUp> = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(
                    call : Call<ResponseSignUp>,
                    response: Response<ResponseSignUp>
            ){
                if (response.isSuccessful){
                    val data = response.body()?.data

                    Toast.makeText(this@SignUpActivity,"${data?.id}님 회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }else Toast.makeText(this@SignUpActivity,"회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }

    /*
    //회원가입 완료 버튼 클릭시
    private fun initAuthButtonClickListeners(){
        binding.btnSignupDone.setOnClickListener {
            val name = binding.etSignupName.text.toString()
            val id = binding.etSignupId.text.toString()
            val pw = binding.etSignupPw.text.toString()

            //name,id,pw 셋 중 하나라도 비어있다면
            if(name.isBlank() || id.isBlank() || pw.isBlank()){
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
     */
}