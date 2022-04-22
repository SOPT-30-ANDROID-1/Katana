# Seminar2
---

### # ItemDecoration
> RecyclerView 아이템 간 간격 주기
``` kotlin
class ItemDecoration (context: Context, private val spanCount : Int) 
: RecyclerView.ItemDecoration() {
 
override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
    super.getItemOffsets(outRect, view, parent, state)
    }
}
```
+ __ItemDecoration__ : RecyclerView 내부에 있는 추상 클래스로 RecyclerView의 아이템을 꾸미는 역할
+ __getItemOffsets()__ : 아이템 간 간격을 지정해 주는 메서드 
   + ItemDecoration 클래스의 생성자로 spanCount를 인자로 설정
   + spanCount에 따라 간격을 다르게 적용

``` kotlin
binding.rvFollower.addItemDecoration(ItemDecoration(requireContext(),1)) 
```
+ RecyclerView에 적용 

### # ItemTouchHelperCallback
> RecyclerView Item 이동 삭제 구현
+ __ItemTouchHelper__ : RecyclerView에 삭제를 위한 스와이프 및 드래그 앤 드롭을 지원하는 유틸리티 클래스
``` kotlin
interface ItemTouchHelperListener{
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}
```
+ ItemTouchHelperListener 인터페이스 선언
   + RecyclerView의 Adapter와 ItemTouchHelper.Callback을 연결시켜 주는 리스너
``` kotlin
class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener):ItemTouchHelper.Callback(){
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //드래그 방향
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        //스와이프 방향
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
 }
```
+ ItemTouchHelperCallback 클래스 생성
   + ItemTouchHelper.Callback 클래스를 상속

``` kotlin
class FollowerAdapter :  RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),ItemTouchHelperCallback.ItemTouchHelperListener{

    //드래그 호출 메소드
    override fun onItemMove(from_position: Int, to_position: Int) : Boolean {
        val user = userList[from_position]

        userList.removeAt(from_position)
        userList.add(to_position,user)

        notifyItemMoved(from_position, to_position)
        return true
    }

    //스와이프 호출 메소드
    override fun onItemSwipe(position: Int){
        userList.removeAt(position)
        notifyItemRemoved(position)
    }
}
```  
+ Adapter에 ItemTouchHelperListener 리스너 구현
   + onItemMove 메소드와 onItemSwipe 메소드 오버라이드

``` kotlin
val itemTouchHelperCallback = ItemTouchHelperCallback(followerAdapter)

        val helper = ItemTouchHelper(itemTouchHelperCallback)
        helper.attachToRecyclerView(binding.rvFollower)
```
+ RecyclerView에 적용

> button/Item Layout background 설정 
#### # button_background.xml
``` kotlin 
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <gradient
        android:angle="0"
        android:endColor="#2ebf91"
        android:startColor="#8360c3"
        android:type="linear" />
    <corners
        android:bottomLeftRadius="15dp"
        android:bottomRightRadius="15dp"
        android:topLeftRadius="15dp"
        android:topRightRadius="15dp" />
    <padding
        android:bottom="0dp"
        android:left="0dp"
        android:right="0dp"
        android:top="0dp" />
</shape>
```
#### # activity_home.xml
``` kotlin
android:background="@drawable/button_background" 
```
+ drawable 폴더에 button_background.xml 파일 생성
   + button에서 background 속성으로 적용

> TextView의 내용이 길어질 경우
``` kotlin
android:ellipsize="end"
android:maxLines="1" 
``` 
+ __ellipsize 속성 end 사용__ : 뒷 부분을 ... 으로 표시
+ __maxLines 속성 사용__ : TextView의 라인 수를 지정


### # 실행 화면 

<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/62695395/164729567-fdcf4af2-3712-49b3-86c9-3759bab0a746.gif" width="270" height="480" /></td><td><img src="https://user-images.githubusercontent.com/62695395/164729802-87348470-1990-490e-bddb-090043eab048.gif"  width="270" height="480" /></td>
  <tr>
</table>

