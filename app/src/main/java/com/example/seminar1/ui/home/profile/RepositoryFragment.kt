package com.example.seminar1.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seminar1.util.ItemDecoration
import com.example.seminar1.ui.adapter.RepositoryAdapter
import com.example.seminar1.data.RepoData
import com.example.seminar1.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter: RepositoryAdapter
    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter(){
        repositoryAdapter = RepositoryAdapter()
        binding.rvRepo.addItemDecoration(ItemDecoration(requireContext(),2))
        binding.rvRepo.adapter = repositoryAdapter

        repositoryAdapter.repoList.addAll(
                listOf(
                        RepoData("안드로이드\n레포지토리","안드로이드 과제 레포지토리 입니다."),
                        RepoData("IOS\n레포지토리","IOS 과제 레포지토리 입니다."),
                        RepoData("서버\n레포지토리","서버 과제 레포지토리 입니다."),
                        RepoData("웹\n레포지토리","웹 과제 레포지토리 입니다."),
                        RepoData("기획\n레포지토리","기획 과제 레포지토리 입니다."),
                        RepoData("디자인\n레포지토리","디자인 과제 레포지토리 입니다.")
                )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}