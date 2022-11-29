package com.example.pasport.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MyUserDao {

    @Insert
    fun addPassport(user: User)

    @Query("select * from user")
    fun getPassport():List<User>

}