package com.example.phonebook.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.phonebook.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UsersRepository(application: Application): CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
    private var usersDao: UserDAO
    private var users: LiveData<List<User>>

    init {
        val database = UsersDatabase.getInstance(application)
        usersDao = database!!.usersDao()
        users = usersDao.getAll()
    }

    fun getUserById(id: String, onUserLoaded: (User) -> Unit) {
        launch {
            val user = usersDao.getById(id)
            onUserLoaded(user!!)
        }
    }

    fun isUserExists(id: String, onExists: (Boolean)->Unit) {
        launch {
            var exists = false
            val user = usersDao.getById(id)
            if (user != null) {
                exists = true
            }
            onExists(exists)
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return users
    }

    fun insertUser(user: User) {
        launch {
            usersDao.insert(user)
        }
    }

    fun updateUser(user: User) {
        launch {
            usersDao.update(user)
        }
    }

    fun deleteUser(user: User) {
        launch {
            usersDao.delete(user)
        }
    }
}