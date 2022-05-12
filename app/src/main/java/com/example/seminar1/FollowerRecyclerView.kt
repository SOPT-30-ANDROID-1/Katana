package com.example.seminar1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.seminar1.databinding.FragmentFollowerRecyclerViewBinding

class FollowerRecyclerView : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerRecyclerViewBinding.inflate(layoutInflater,container,false)

        initAdapter()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()
        binding.rvFollower.addItemDecoration(ItemDecoration(requireContext(),1))
        binding.rvFollower.adapter = followerAdapter

        followerAdapter.userList.addAll(
                listOf(
                        UserData("김다희","안드로이드 파트원"),
                        UserData("김다희","안드로이드 파트원"),
                        UserData("김다희","안드로이드 파트원"),
                        UserData("김다희","안드로이드 파트원")
                )
        )
        followerAdapter.notifyDataSetChanged()

        followerAdapter.setItemClickListener(object : FollowerAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                val name = followerAdapter.userList[position].name
                val introduce = followerAdapter.userList[position].introduction

                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("name",name)
                    putExtra("introduce",introduce)
                }
                startActivity(intent)

            }
        })

        val itemTouchHelperCallback = ItemTouchHelperCallback(followerAdapter)

        val helper = ItemTouchHelper(itemTouchHelperCallback)
        helper.attachToRecyclerView(binding.rvFollower)

    }



}