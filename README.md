# Seminar4
--- 
  
> 로그인/회원가입 API 연동하기

### # Postman 테스트 
+ /user/signup 경로에서 회원가입 API 테스트
<img width="560" height="420" alt="signup" src="https://user-images.githubusercontent.com/62695395/168273413-b9e216aa-709f-461c-a3eb-fa65762a430e.png">

+ /user/signin 경로에서 로그인 API 테스트
<img width="560" height="420" src="https://user-images.githubusercontent.com/62695395/168273597-69008851-c97d-48d5-a5f6-07c2989836cc.png">

  
> Github API 연동해서 리스트로 띄우기  

#### # ResponseFollower 
``` kotlin
data class ResponseFollower(
        val login: String,
        val html_url: String,
        val avatar_url: String
)
``` 

#### # ResponseUser
``` kotlin
data class ResponseUser(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("followers_url")
        val followersUrl: String,
        @SerializedName("following_url")
        val followingUrl: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String
)
```

#### # GithubService 
``` kotlin
interface GithubService {
    @GET("users/{login}/followers")
    fun getFollowers(
            @Path("login") login: String
    ): Call<List<ResponseFollower>>

    @GET("users/{login}")
    fun getUser(
            @Path("login") login: String
    ): Call<ResponseUser>
}
```

#### # ServiceCreator
``` kotlin
object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GITHUB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val githubService: GithubService = githubRetrofit.create(GithubService::class.java)
}
```

#### # FollowerFragment
``` kotlin
    private fun followerNetwork(){
        val call : Call<List<ResponseFollower>> = ServiceCreator.githubService.getFollowers("kimdahee7")

        call.enqueue(object : Callback<List<ResponseFollower>> {
            override fun onResponse(
                    call : Call<List<ResponseFollower>>,
                    response: Response<List<ResponseFollower>>
            ){
                if (response.isSuccessful){
                    val data = response.body()!!
                    for(i in data.indices){
                        val login = data[i].login
                        val introduce = data[i].html_url
                        val imgUrl = data[i].avatar_url

                        followerAdapter.userList.add(UserData(login,introduce,imgUrl))
                        followerAdapter.notifyDataSetChanged()
                    }
                }else{
                    Toast.makeText(context, "팔로워 리스트를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseFollower>>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }
```
+ Call을 생성하고 비동기 처리를 위해 queue에 넣어준다. 
+ 팔로워 API의 {login}에는 팔로워 목록을 불러오고자 하는 깃허브 아이디를 넣어준다. 

### # 실행 화면 
<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/62695395/168271497-c1b30f31-b517-43e0-9eaa-69777a757b3a.gif" width="270" height="480" /></td><td><img src="https://user-images.githubusercontent.com/62695395/168271551-d3b79a01-c126-4fdf-a999-92ebf5bf2d23.gif"  width="270" height="480" /></td>
  <tr>
</table>

                                                                                                                                         
                                                                                                                                         
