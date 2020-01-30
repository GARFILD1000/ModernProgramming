package com.example.phonebook.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phonebook.App
import com.example.phonebook.model.User
import com.example.phonebook.repo.UsersRepository

class UserListViewModel: ViewModel() {
    val repo = UsersRepository(App.instance)
    val users: LiveData<List<User>> = repo.getAllUsers()
    var currentUser: User? = null

}