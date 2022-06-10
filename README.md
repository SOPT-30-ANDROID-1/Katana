# Seminar7
--- 
#### # SOPTSharedPreferences
``` kotlin
object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY,Context.MODE_PRIVATE)
    }

    fun getAutoLogin(context: Context): Boolean {
        return init(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean){
        init(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: Context){
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}
```
+ __SharedPreferences__ : key-value 방식으로 간단하게 데이터를 저장하는 방식
    + 앱 전역에서 호출되므로 싱글톤 객체로 생성하여 관리
+ __getSharedPreferences() Activity 모드__ 
    + MODE_PRIVATE : 해당 데이터는 해당 앱에서만 사용 가능
 
> 자동 로그인 
#### # SignInActivity
``` kotlin
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
```
+ 자동 로그인 체크박스가 선택되었을 경우 true, 아닐 경우 false를 SharedPreferences에 저장
    + true일 경우 HomeActivity가 바로 실행되도록 함
 
> 자동 로그인 해제
 
#### # SettingActivity
``` kotlin
        binding.btnLogout.setOnClickListener{
            SOPTSharedPreferences.setLogout(this)
            showToast("자동로그인이 해제되었습니다.")
        }
``` 
 
### # 실행 화면
<table>
  <tr>
<td><img src="https://user-images.githubusercontent.com/62695395/173039998-63453a8e-39df-433a-a9e8-aaa0b23d3ccf.gif" width="270" height="480" /></td>
    <td><img src="https://user-images.githubusercontent.com/62695395/173040094-08c0bcda-4ae7-4889-a5cf-cf6f5206f603.gif" width="270" height="480" /></td>
<td><img src="https://user-images.githubusercontent.com/62695395/173040236-9e5d1695-535c-4559-84d0-24d7b8ea6199.gif" width="270" height="480" /></td>
  <tr>
</table>
