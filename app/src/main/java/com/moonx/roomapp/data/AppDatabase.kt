package com.moonx.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moonx.roomapp.models.Note
import com.moonx.roomapp.models.Setting
import com.moonx.roomapp.models.User

@Database(entities = [Setting::class,User::class, Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempIns = INSTANCE
            if (tempIns != null)
                return tempIns


            synchronized(this) {
                val ins: AppDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = ins
                return ins
            }
        }
    }

}