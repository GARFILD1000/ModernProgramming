package com.example.phonebook.repo

import com.example.phonebook.App
import com.example.phonebook.model.User
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object LocalStorageService {
    const val RESERVE_FOLDER = "reserve"
    const val MAX_RESERVE = 1024 * 10 // 10 MB

    var gson = GsonBuilder().create()

    fun getReserveFolder(): File?{
        var reserveDir: File? = null
        val externalDir = App.instance.getExternalFilesDir(null)
        if (externalDir != null) {
            reserveDir = File(externalDir, RESERVE_FOLDER)
            if (!reserveDir.exists()) {
                reserveDir.mkdir()
            }
        }
        return reserveDir
    }

    fun saveUsersReserve(users: User.UsersList) {
        val reserveFolder = getReserveFolder()
        if (reserveFolder != null){
            val currentTime = SimpleDateFormat.getTimeInstance().format(Date())
            val currentDate = SimpleDateFormat.getDateInstance().format(Date())
            val reserveFileName = "${currentDate} ${currentTime}.json"
            val reserveFilePath = File(reserveFolder, reserveFileName).apply{
                createNewFile()
            }.absolutePath
            val jsonData = gson.toJson(users)
            writeFile(reserveFilePath, jsonData.toByteArray())
        }
    }

    fun clearReserveIfNeed() {
        getReserveFolder()?.let{reserveFolder ->
            if (reserveFolder.totalSpace > MAX_RESERVE) {
                val files = reserveFolder.listFiles()
                files ?: return
                files.sortWith(kotlin.Comparator { o1, o2 -> (o1.lastModified() - o2.lastModified()).toInt() })
                for (file in files) {
                    file.delete()
                    if (reserveFolder.totalSpace < MAX_RESERVE) break
                }
            }
        }
    }

    private fun readFile(fullFilePath: String): String{
        var readedValue = ""
        try{
            readedValue = FileInputStream(fullFilePath).run{
                bufferedReader().use {
                    it.readText()
                }
            }
        }
        catch(ex : IOException){
            ex.printStackTrace()
        }
        return readedValue
    }

    private fun writeFile(fullFilePath: String, data: ByteArray) : Boolean{
        var writed = false
        try{
            FileOutputStream(fullFilePath).use{
                it.write(data)
                it.close()
                writed = true
            }
        }
        catch(ex : IOException){
            ex.printStackTrace()
        }
        return writed
    }

}