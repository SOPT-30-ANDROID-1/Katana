# Seminar1
---
### # SignInActivity
> 아이디, 비밀번호 모두 입력이 되어있을 때만 HomeActivity로 화면 전환
``` kotlin
private fun ClickSignIn(){
    binding.btnLogin.setOnClickListener {
        val id = binding.etId.text.toString()
        val pw = binding.etPw.text.toString()

        //id,pw 둘 중 하나라도 비어있다면
        if(id.isEmpty() || pw.isEmpty()){
            Toast.makeText(this,"아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()

        }else{ //모두 채워져있을 경우
            Toast.makeText(this,"로그인 성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
```
+ __isEmpty() 함수 사용__ : 문자열이 null이거나 0-length이면 true를 반환
   + ‘OR’을 의미하는 논리 연산자 ||를 사용하여 id, pw 둘 중 하나라도 비어있으면 “아이디/비밀번호를 확인해주세요”라는 토스트 메시지를 출력한다.
   + 그 외(모두 채워져있을 때)는 “로그인 성공”이라는 토스트 메시지를 띄우며 Intent를 사용해 HomeActivity로 이동한다.


> 회원가입에서 입력했던 데이터 받아오기
``` kotlin
private fun setResultSignUp(){
    resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> if(result.resultCode == Activity.RESULT_OK){
        result.data.let {
            val userId = it?.getStringExtra("id")
            val userPw = it?.getStringExtra("pw")
            binding.etId.setText(userId)
            binding.etPw.setText(userPw)
        }
      }
    }
}
```
+ __registerForActivityResult() 함수 사용__
   + result개체로부터 resultCode를 확인하고 intent로부터 데이터를 받아온다.

> 회원가입 버튼 클릭 시 SignUpActivity로 화면 전환
``` kotlin
private fun ClickSignUp(){
    binding.btnSignup.setOnClickListener{
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }
}
```
+ 위에서 registerForActivityResult() 함수를 담은 변수에 .launch(intent)하여 SignUpAcitivty로 이동한다.

### # SignUpActivity

> 회원가입에서 입력했던 데이터 보내기
``` kotlin
private fun ClickSignUpDone(){
    binding.btnSignupDone.setOnClickListener {
        val name = binding.etSignupName.text.toString()
        val id = binding.etSignupId.text.toString()
        val pw = binding.etSignupPw.text.toString()

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
``` 
+ putExtra로 데이터를 넘겨준다

> 비밀번호 EditTextView의 입력 내용 가리기
``` kotlin
android:inputType="textPassword"
```
+ inputType 속성 textPassword 사용

> EditTextView 미리보기 글씨 나타내기
``` kotlin
android:hint="비밀번호를 입력해주세요."
```
+ hint속성 사용

> 자기소개 화면에 스크롤 기능 적용하기
``` kotlin
<ScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="20dp"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/tv_mbti">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginHorizontal="40dp"
            android:text="안녕하세요 안드로이드 파트 김다희입니다.“
            android:textColor="#000000"
            android:textSize="20dp" />
    </LinearLayout>
</ScrollView>
```
+ 한 개의 ScrollView는 오직 한 개의 뷰만을 가질 수 있다.
   + ScrollView 안에 여러 뷰를 넣어줄 경우 LinearLayout을 포함해 사용한다.

> 사진 비율 1:1 설정하기
``` kotlin
<ImageView
    android:id="@+id/image"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/ic_launcher"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="60dp"
    app:layout_constraintDimensionRatio="1:1"
    app:layout_constraintStart_toStartOf="parent"/>
```
+ constraintDimensionRatio 속성 1:1 사용


### # 실행 화면 

<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/62695395/162607814-69d424e2-c2cd-4a38-b172-8648c70726b0.gif" width="270" height="480" /></td><td><img src="https://user-images.githubusercontent.com/62695395/162607886-2ec75c61-5034-4262-bba4-1dea58478156.gif"  width="270" height="480" /></td><td><img src="https://user-images.githubusercontent.com/62695395/162608078-d87fcc10-77a5-49cd-8158-37e44b2c56a8.gif" width="270" height="480" /></td>
  <tr>
</table>


                                                                                                                                         
                                                                                                                                         
