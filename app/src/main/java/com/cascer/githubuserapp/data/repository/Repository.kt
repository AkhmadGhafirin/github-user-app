package com.cascer.githubuserapp.data.repository

import androidx.lifecycle.LiveData
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.utils.network.Results

interface Repository {
    suspend fun searchUser(query: String): Results<List<UserModel>>
    suspend fun userDetail(username: String): Results<UserModel>
    suspend fun userFollowing(username: String): Results<List<UserModel>>
    suspend fun userFollowers(username: String): Results<List<UserModel>>
    suspend fun getFavorites(): LiveData<List<UserModel>>
    fun getFavoriteByUsername(login: String): LiveData<UserModel>
    suspend fun insertFavorite(userModel: UserModel)
    suspend fun deleteFavorite(userModel: UserModel)
    suspend fun updateFavorite(userModel: UserModel)
}