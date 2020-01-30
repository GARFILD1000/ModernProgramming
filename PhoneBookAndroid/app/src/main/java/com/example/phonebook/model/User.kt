package com.example.phonebook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    companion object{

    }
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var firstName = ""
    var lastName = ""
    var mobilePhone = ""
    var urlAddress = ""
    var email = ""

    class Builder{
        var user = User()

        fun setFirstName(firstName: String): Builder{
            user.firstName = firstName
            return this
        }

        fun setLastName(lastName: String): Builder{
            user.lastName = lastName
            return this
        }

        fun setMobilePhone(mobilePhone: String): Builder{
            user.mobilePhone = mobilePhone
            return this
        }

        fun setEmail(email: String): Builder{
            user.email = email
            return this
        }

        fun setUrlAddress(urlAddress: String): Builder{
            user.urlAddress = urlAddress
            return this
        }

        fun build(): User{
            return user
        }
    }

    data class UsersList(var users: List<User>)

}
