package com.cascer.githubuserapp.data.model.mapper

import com.cascer.githubuserapp.data.model.UserModel
import com.cascer.githubuserapp.data.model.UserResponse

object Mapper {

    fun UserResponse.toModel() = UserModel(
        avatarUrl = avatarUrl.orEmpty(),
        name = name.orEmpty(),
        login = login.orEmpty(),
        followers = followers ?: 0,
        following = following ?: 0
    )

    fun emptyUserModel() = UserModel(
        avatarUrl = "",
        name = "",
        login = "",
        followers = 0,
        following = 0
    )
}