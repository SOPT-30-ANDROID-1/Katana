# Seminar3
--- 
### # ProfileFragment 
> Button에 Selector 활용하기 
``` kotlin
 <selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/shape_button" android:state_selected="true"/>
    <item android:drawable="@drawable/shape_unselect_button" />
</selector>
```
+ selector를 활용해 Button을 클릭했을 때와 안 했을 때 버튼 색상을 변경해 준다. 
    + state_selected의 true/false로 색상 구분 
 

> 이미지 원형으로 표시하기 
``` kotlin
implementation 'com.github.bumptech.glide:glide:4.13.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
```
+ build.gradle(app)에 라이브러리 추가
 
``` kotlin
    private fun initImage(){
        Glide.with(this)
                .load(R.drawable.profile3)
                .circleCrop()
                .into(binding.ivProfile)
    }
```
+ Glide 이용해서 동그란 이미지 만들기 
 
### # HomeFragment 
> TabLayout에 텍스트, 인디케이터 색상 설정하기 
``` kotlin
app:tabIndicatorColor="@color/selector_color"
app:tabSelectedTextColor="@color/sopt_main_purple"
```

### # NestedScrollableHost
> 중첩 스크롤 문제 해결하기 
+ __문제__ : ViewPager2 스크롤 보기가 ViewPager2가 포함된 외부 개체와 방향이 같은 경우 기본적으로 중첩된 스크롤 뷰를 지원하지 않아 작동하지 않는다. 
 
+ __해결 방법__ : ViewPager2 객체의 requestDisallowInterceptTouchEvent()를 호출해야 한다. 
    + 구글에서 제공하는(requestDisallowInterceptTouchEvent()를 호출하고 있는) __NestedScrollableHost.kt__ 를 추가해 준다. 
    + 해당 xml 파일에서 적용해 준다. 
``` kotlin
    <com.example.seminar1.NestedScrollableHost
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tab_home">
        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/vp_home"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#F1F0F0"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/tab_home" />
    </com.example.seminar1.NestedScrollableHost>
```
### # 실행 화면 

<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/62695395/167148577-1563c1de-142c-471b-9134-06410c4efe69.gif" width="270" height="480" /></td><td><img src="https://user-images.githubusercontent.com/62695395/167148688-7e54e6ff-e39e-4ac9-9852-6da97a8d8e3a.gif"  width="270" height="480" /></td>
  <tr>
</table>

