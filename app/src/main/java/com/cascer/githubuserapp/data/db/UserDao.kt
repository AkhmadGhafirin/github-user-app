package com.cascer.githubuserapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cascer.githubuserapp.data.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserModel)

    @Update
    fun update(user: UserModel)

    @Delete
    fun delete(user: UserModel)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun fetchAll(): LiveData<List<UserModel>>

    @Query("SELECT * FROM user WHERE login = :login")
    fun fetchByUsername(login: String): LiveData<UserModel>
}