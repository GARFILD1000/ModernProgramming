package com.example.phonebook.ui.fragment

import android.os.Bundle
import android.os.LocaleList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.App
import com.example.phonebook.R
import com.example.phonebook.adapter.UserAdapter
import com.example.phonebook.model.User
import com.example.phonebook.repo.LocalStorageService
import com.example.phonebook.ui.activity.MainActivity
import com.example.phonebook.vm.UserListViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment(){
    val userAdapter = UserAdapter()
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var userListViewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListViewModel = ViewModelProviders.of(activity!!).get(UserListViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        userAdapter.onClickItem = {
            userListViewModel.currentUser = it
            (context as? MainActivity)?.goCreateUser()
        }

        recyclerView.apply{
            adapter = userAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

        userListViewModel.users.observe(viewLifecycleOwner, Observer{
            userAdapter.setItems(it)
            if (App.needSaveReserve && context != null){
                App.needSaveReserve = false
                LocalStorageService.saveUsersReserve(User.UsersList(it))
            }
        })

        createUser.setOnClickListener{
            userListViewModel.currentUser = null
            (context as? MainActivity)?.goCreateUser()
        }
    }

}