package com.example.phonebook.repo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.phonebook.model.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id LIKE :id")
    suspend fun getById(id: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}