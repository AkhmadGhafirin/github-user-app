package com.cascer.githubuserapp.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.databinding.ItemUserBinding
import com.cascer.githubuserapp.utils.ImageUtils.loadCircle

class MainAdapter(
    private val listener: (user: UserModel) -> Unit
) : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

    private var listItem = mutableListOf<UserModel>()

//    fun sendData(data: List<UserModel>) {
//        listItem.clear()
//        listItem.addAll(data)
//        notifyDataSetChanged()
//    }

    fun sendData(data: List<UserModel>) {
        val diffCallback = UserDiffCallback(listItem, data)
        val diffUsers = DiffUtil.calculateDiff(diffCallback)
        listItem.clear()
        listItem.addAll(data)
        diffUsers.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        listItem[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listItem.size

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.invoke(listItem[adapterPosition])
            }
        }

        fun bind(item: UserModel) {
            binding.tvUsername.text = item.login
            binding.ivUser.loadCircle(binding.root.context, item.avatarUrl)
        }
    }
}