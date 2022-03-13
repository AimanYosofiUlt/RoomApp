package com.moonx.roomapp.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.moonx.roomapp.data.AppDatabase
import com.moonx.roomapp.models.User

class UserRepo(private val application: Application) {
    val appDao = AppDatabase.getDatabase(application).appDao()
    val users: LiveData<List<User>> = appDao.selectAllUsers()

    suspend fun addUser(user: User) {
        appDao.addUser(user)
    }

    fun deleteUser(user: User) {
        appDao.deleteUser(user)
        appDao.deleteUserNote(user.id)
    }
}