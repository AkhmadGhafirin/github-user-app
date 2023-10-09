package com.cascer.githubuserapp.data.repository

import com.cascer.githubuserapp.data.api.ApiService
import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.data.model.mapper.Mapper.emptyUserModel
import com.cascer.githubuserapp.data.model.mapper.Mapper.toModel
import com.cascer.githubuserapp.utils.ExceptionUtil.toException
import com.cascer.githubuserapp.utils.network.Results
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun searchUser(query: String): Results<List<UserModel>> {
        return try {
            val request = apiService.searchUser(query)
            if (request.isSuccessful) {
                Results.Success(request.body()?.items?.map { it.toModel() } ?: listOf())
            } else {
                Results.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

    override suspend fun userDetail(username: String): Results<UserModel> {
        return try {
            val request = apiService.userDetail(username)
            if (request.isSuccessful) {
                Results.Success(request.body()?.toModel() ?: emptyUserModel())
            } else {
                Results.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

    override suspend fun userFollowing(username: String): Results<List<UserModel>> {
        return try {
            val request = apiService.userFollowing(username)
            if (request.isSuccessful) {
                Results.Success(request.body()?.map { it.toModel() } ?: listOf())
            } else {
                Results.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

    override suspend fun userFollowers(username: String): Results<List<UserModel>> {
        return try {
            val request = apiService.userFollowers(username)
            if (request.isSuccessful) {
                Results.Success(request.body()?.map { it.toModel() } ?: listOf())
            } else {
                Results.Error(request.errorBody().toException())
            }
        } catch (e: Exception) {
            Results.Error(e)
        }
    }
}