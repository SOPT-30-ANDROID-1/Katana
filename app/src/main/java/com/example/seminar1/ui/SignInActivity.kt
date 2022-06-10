package com.example.seminar1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.seminar1.util.SOPTSharedPreferences
import com.example.seminar1.data.ServiceCreator
import com.example.seminar1.data.sopt.RequestSignIn
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

        initAuthButtonClickListeners()
        initEvent()
        isAutoLogin()

        setContentView(binding.root)
    }

    private fun initAuthButtonClickListeners() {
        //로그인 버튼 클릭 시
        binding.btnLogin.setOnClickListener{
            loginNetwork()
        }
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
        binding.ibCheck.setOnClickListener{
            binding.ibCheck.isSelected = !binding.ibCheck.isSelected

            SOPTSharedPreferences.setAutoLogin(this,binding.ibCheck.isSelected)
        }
    }

    private fun isAutoLogin(){
        if (SOPTSharedPreferences.getAutoLogin(this)){
            showToast("자동로그인 되었습니다.")
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
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
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                }else {
                    onError?.invoke(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseType>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    private fun loginNetwork(){
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueueUtil(
            onSuccess = {
                showToast("로그인에 성공하였습니다.")
            },
            onError = {
                showToast("로그인에 실패하였습니다.")
            }
        )
    }

    fun Context.showToast(msg: String){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
    }
}
