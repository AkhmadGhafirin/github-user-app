package com.cascer.githubuserapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String?,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "followers")
    val followers: Int?,
    @ColumnInfo(name = "following")
    val following: Int?
)