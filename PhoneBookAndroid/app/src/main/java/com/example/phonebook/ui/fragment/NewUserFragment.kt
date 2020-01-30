package com.example.phonebook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.phonebook.R
import com.example.phonebook.model.User
import com.example.phonebook.ui.activity.MainActivity
import com.example.phonebook.vm.UserListViewModel
import kotlinx.android.synthetic.main.fragment_new_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewUserFragment : Fragment(), CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    lateinit var userListViewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel = ViewModelProviders.of(activity!!).get(UserListViewModel::class.java)
        userListViewModel.currentUser?.let{
            showUser(it)
        }
        cancelButton.setOnClickListener {
            cancel()
        }
        saveUserButton.setOnClickListener {
            saveUser()
        }
        deleteUserButton.setOnClickListener {
            deleteUser()
        }

    }

    private fun showUser(user: User){
        userFirstNameEditText.setText(user.firstName)
        userLastNameEditText.setText(user.lastName)
        userMobilePhoneEditText.setText(user.mobilePhone)
        userEmailEditText.setText(user.email)
        userUrlEditText.setText(user.urlAddress)
        deleteUserButton.visibility = View.VISIBLE
    }

    private fun saveUser(){
        val user = userListViewModel.currentUser?.apply{
            fillUserData(this)
        }?:createUser()

        userListViewModel.repo.insertUser(user)

        cancel()
    }

    private fun fillUserData(user: User) {
        user.firstName = userFirstNameEditText.text.toString()
        user.lastName = userLastNameEditText.text.toString()
        user.mobilePhone = userMobilePhoneEditText.text.toString()
        user.email = userEmailEditText.text.toString()
        user.urlAddress = userUrlEditText.text.toString()
    }

    private fun createUser(): User {
        val firstName = userFirstNameEditText.text.toString()
        val lastName = userLastNameEditText.text.toString()
        val mobilePhone = userMobilePhoneEditText.text.toString()
        val email = userEmailEditText.text.toString()
        val urlAddress = userUrlEditText.text.toString()
        return User.Builder().setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setMobilePhone(mobilePhone)
            .setUrlAddress(urlAddress)
            .build()
    }

    private fun deleteUser() {
        userListViewModel.currentUser?.let {
            userListViewModel.repo.deleteUser(it)
        }
        cancel()
    }

    private fun cancel(){
        activity?.onBackPressed()
    }

}