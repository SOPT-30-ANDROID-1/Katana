package com.example.seminar1.ui

import android.app.Activity
import android.content.Context
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

    fun <ResponseType> Call<ResponseType>.enqueueUtil(
        onSuccess: (ResponseType) -> Unit,
        onError: ((stateCode:Int) -> Unit)? = null
    ){
        this.enqueue(object : Callback<ResponseType>{
            override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
                if (response.isSuccessful){
                    onSuccess.invoke(response.body() ?: return)
                }else {
                    onError?.invoke(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    private fun signUpNetwork(){
        val requestSignUp = RequestSignUp(
                id = binding.etSignupId.text.toString(),
                name = binding.etSignupName.text.toString(),
                password = binding.etSignupPw.text.toString()
        )

        val call = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueueUtil(
            onSuccess = {
                it.data
            },
            onError = {
                showToast("회원가입에 실패하였습니다.")
            }
        )
    }

    fun Context.showToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}