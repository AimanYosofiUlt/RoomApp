package com.moonx.roomapp.repos

import android.app.Application
import com.moonx.roomapp.data.AppDatabase
import com.moonx.roomapp.models.Setting
import com.moonx.roomapp.models.User

class SettingRepo(private val application: Application) {
    val appDao = AppDatabase.getDatabase(application).appDao()
    var setting = appDao.getSetting()

    suspend fun initSetting() {
        appDao.initSetting(Setting(0, 0))
    }

    fun setCurrentUser(user: User) {
        val tempSetting = Setting(0, user.id)
        appDao.setSetting(tempSetting)
    }
}