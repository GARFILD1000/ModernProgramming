package com.example.phonebook.ui.activity

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.phonebook.App
import com.example.phonebook.R

class MainActivity : AppCompatActivity() {
    companion object{
        const val PERMISSIONS_REQUEST_CODE = 123
    }

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        checkAllNeededPermissions()
    }

    private fun checkAllNeededPermissions() {
        val application = (applicationContext as App)
        val neededPermissions = arrayOf(Manifest.permission.INTERNET)

        val allPermissionsGranted = application.checkPermissions(this, *neededPermissions)
        if (!allPermissionsGranted) {
            application.requestPermissions(this, PERMISSIONS_REQUEST_CODE, *neededPermissions)
        }
    }

    fun goCreateUser(){
        navController.navigate(R.id.newUserFragment)
    }
}
