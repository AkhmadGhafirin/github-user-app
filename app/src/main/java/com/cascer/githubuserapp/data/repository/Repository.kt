package com.cascer.githubuserapp.data.repository

import com.cascer.githubuserapp.data.db.entity.UserEntity
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.utils.network.Results

interface Repository {
    suspend fun searchUser(query: String): Results<List<UserModel>>
    suspend fun userDetail(username: String): Results<UserModel>
    suspend fun userFollowing(username: String): Results<List<UserModel>>
    suspend fun userFollowers(username: String): Results<List<UserModel>>
    suspend fun getFavorites(): List<UserModel>
    suspend fun getFavoriteByUsername(login: String): UserModel
    suspend fun insertFavorite(user: UserEntity)
    suspend fun deleteFavorite(id: Int)
    suspend fun updateFavorite(user: UserEntity)
}