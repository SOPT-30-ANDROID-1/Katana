package com.example.seminar1.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.seminar1.*
import com.example.seminar1.adapter.FollowerAdapter
import com.example.seminar1.data.UserData
import com.example.seminar1.databinding.FragmentFollowerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        followerNetwork()
    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()
        binding.rvFollower.addItemDecoration(ItemDecoration(requireContext(),1))
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.setItemClickListener { view, position ->
            val name = followerAdapter.userList[position].name
            val introduce = followerAdapter.userList[position].introduction
            val profile = followerAdapter.userList[position].profile

            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", name)
                putExtra("introduce", introduce)
                putExtra("profile", profile)
            }
            startActivity(intent)
        }

        val itemTouchHelperCallback = ItemTouchHelperCallback(followerAdapter)

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvFollower)

    }

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
                        val imgUrl = data[i].avatar_url
                        val introduce = data[i].html_url

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}