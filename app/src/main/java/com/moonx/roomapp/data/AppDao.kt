package com.moonx.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moonx.roomapp.models.Note
import com.moonx.roomapp.models.Setting
import com.moonx.roomapp.models.User

@Dao
interface AppDao {

    @Query("select * from setting_table")
    fun getSetting(): LiveData<List<Setting>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initSetting(setting: Setting)

    @Update
    fun setSetting(setting: Setting)

    @Query("select * from user_table")
    fun selectAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("Delete from note_table where userId = :userId")
    fun deleteUserNote(userId: Int)

    @Query("select * from note_table where userId = :currentUserId")
    fun getNotes(currentUserId: Int): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    fun editNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

//    @Query("select * from note_table wh ere exists(select * from note_table where title = :title)")
//    fun isExists(title: String):LiveData<Int>
}