package com.moonx.roomapp.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.moonx.roomapp.models.Setting
import com.moonx.roomapp.models.User
import com.moonx.roomapp.repos.NoteRepo
import com.moonx.roomapp.repos.SettingRepo
import com.moonx.roomapp.repos.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepo = UserRepo(application)
    var users: LiveData<List<User>> = userRepo.users
    private var settingRepo = SettingRepo(application)
    var setting: LiveData<List<Setting>> = settingRepo.setting

    fun initSetting() {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepo.initSetting()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteUser(user)
        }
    }

    fun setCurrentUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            settingRepo.setCurrentUser(user)
        }
    }
}