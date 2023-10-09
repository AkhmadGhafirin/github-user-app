package com.cascer.githubuserapp.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val avatarUrl: String,
    val login: String,
    val name: String,
    val followers: Int,
    val following: Int
)

data class UserResponse(
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("login") val login: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)

data class GithubResponse(
    @SerializedName("total_count") val totalCount: Int?,
    @SerializedName("incomplete_results") val incompleteResults: Boolean?,
    @SerializedName("items") val items: List<UserResponse>?
)