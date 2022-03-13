package com.moonx.roomapp.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.moonx.roomapp.models.Note
import com.moonx.roomapp.repos.NoteRepo

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepo: NoteRepo = NoteRepo(application)

    fun getNotes(userId: Int): LiveData<List<Note>> {
        return noteRepo.getNotes(userId)
    }
}