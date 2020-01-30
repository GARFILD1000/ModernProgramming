package com.example.phonebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.phonebook.R
import com.example.phonebook.model.User
import kotlinx.android.synthetic.main.fragment_new_user.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    var items = LinkedList<User>()
    var onClickItem: ((User)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.getOrNull(position)?.let{
            holder.bind(it)
        }
    }

    fun setItems(newItems: Collection<User>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size - 1)
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val userFirstName = view.userFirstName
        val userLastName = view.userLastName
        val userMobilePhone = view.userMobilePhone
        val container = view.container

        fun bind(user: User){
            userFirstName.setText(user.firstName)
            userLastName.setText(user.lastName)
            userMobilePhone.setText(user.mobilePhone)
            container.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClickItem?.invoke(user)
                }
            }
        }
    }

}