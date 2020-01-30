package com.example.phonebook.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phonebook.model.User

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase(){
    abstract fun usersDao() : UserDAO
    companion object{
        private var instance: UsersDatabase? = null
        fun getInstance(context : Context) : UsersDatabase?{
            if (instance == null){
                synchronized(UsersDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java,
                        "users_database.db")
                        .build()
                }
            }
            return instance
        }
        fun freeInstance(){
            instance = null
        }
    }
}