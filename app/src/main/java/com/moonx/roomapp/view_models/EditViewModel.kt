package com.moonx.roomapp.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.moonx.roomapp.models.Note
import com.moonx.roomapp.repos.NoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepo = NoteRepo(application)

//    fun isExists(note: Note): LiveData<Int> {
//        return noteRepo.isExist(note)
//    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.addNote(note)
        }
    }

    fun editNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.editNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.deleteNote(note)
        }
    }
}