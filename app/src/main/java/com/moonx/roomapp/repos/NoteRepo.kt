package com.moonx.roomapp.repos

import android.app.Application
import androidx.lifecycle.LiveData
import com.moonx.roomapp.data.AppDatabase
import com.moonx.roomapp.models.Note

class NoteRepo(private val application: Application) {
    private val appDao = AppDatabase.getDatabase(application).appDao()
    lateinit var notes: LiveData<List<Note>>

    fun getNotes(currentUserId: Int):LiveData<List<Note>> {
        return appDao.getNotes(currentUserId)
    }

    suspend fun addNote(note: Note) {
        appDao.addNote(note)
    }

    fun editNote(note: Note) {
        appDao.editNote(note)
    }

    fun deleteNote(note: Note) {
        appDao.deleteNote(note)
    }

//    fun isExist(note: Note): LiveData<Int> {
//      return  appDao.isExists(note.title)
//    }
}