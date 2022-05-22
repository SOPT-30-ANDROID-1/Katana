package com.example.seminar1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar1.data.RepoData
import com.example.seminar1.databinding.ItemList2Binding

class RepositoryAdapter :  RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){
    val repoList = mutableListOf<RepoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
                ItemList2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    class RepositoryViewHolder(
            private val binding: ItemList2Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.tvTitle.text = data.title
            binding.tvContent.text = data.content
        }
    }
}