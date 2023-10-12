package com.cascer.githubuserapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cascer.githubuserapp.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Query("DELETE FROM user WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun fetchAll(): List<UserEntity>

    @Query("SELECT * FROM user WHERE login = :login")
    fun fetchByUsername(login: String): UserEntity
}