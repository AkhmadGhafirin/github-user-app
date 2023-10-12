package com.cascer.githubuserapp.data.model.mapper

import com.cascer.githubuserapp.data.db.entity.UserEntity
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

    fun UserModel.toEntity() = UserEntity(
        avatarUrl = avatarUrl,
        name = name,
        login = login,
        followers = followers,
        following = following
    )

    fun UserEntity?.toModel() = UserModel(
        id = this?.id ?: 0,
        avatarUrl = this?.avatarUrl.orEmpty(),
        name = this?.name.orEmpty(),
        login = this?.login.orEmpty(),
        followers = this?.followers ?: 0,
        following = this?.following ?: 0
    )

    fun emptyUserModel() = UserModel(
        avatarUrl = "",
        name = "",
        login = "",
        followers = 0,
        following = 0
    )
}