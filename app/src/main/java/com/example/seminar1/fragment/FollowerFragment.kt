package com.example.seminar1.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.seminar1.DetailActivity
import com.example.seminar1.ItemDecoration
import com.example.seminar1.ItemTouchHelperCallback
import com.example.seminar1.adapter.FollowerAdapter
import com.example.seminar1.data.UserData
import com.example.seminar1.databinding.FragmentFollowerBinding

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

        followerAdapter.setItemClickListener { view, position ->
            val name = followerAdapter.userList[position].name
            val introduce = followerAdapter.userList[position].introduction

            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", name)
                putExtra("introduce", introduce)
            }
            startActivity(intent)
        }

        val itemTouchHelperCallback = ItemTouchHelperCallback(followerAdapter)

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvFollower)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}