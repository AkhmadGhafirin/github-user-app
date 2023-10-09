package com.cascer.githubuserapp.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.cascer.githubuserapp.data.model.UserModel

class UserDiffCallback(private val oldList: List<UserModel>, private val newList: List<UserModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].login == newList[newItemPosition].login
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].avatarUrl == newList[newItemPosition].avatarUrl
                && oldList[oldItemPosition].followers == newList[newItemPosition].followers
                && oldList[oldItemPosition].following == newList[newItemPosition].following
    }
}