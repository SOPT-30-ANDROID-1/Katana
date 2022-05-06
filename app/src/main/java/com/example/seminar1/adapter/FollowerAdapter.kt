package com.example.seminar1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seminar1.ItemTouchHelperCallback
import com.example.seminar1.R
import com.example.seminar1.data.UserData
import com.example.seminar1.databinding.ItemListBinding

class FollowerAdapter :  RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemTouchHelperCallback.ItemTouchHelperListener {
    val userList = mutableListOf<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
                ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(userList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int = userList.size

    class FollowerViewHolder(
            private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }

    //드래그 호출 메소드
    override fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean {
        val user = userList[fromPosition]

        userList.removeAt(fromPosition)
        userList.add(toPosition,user)

        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    //스와이프 호출 메소드
    override fun onItemSwipe(position: Int){
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

    //클릭 인터페이스 정의
    fun interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }
    //클릭 리스너 선언
    private lateinit var itemClickListener: ItemClickListener

    //클릭 리스너 등록 메소드
    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

}