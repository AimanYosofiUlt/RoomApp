package com.moonx.roomapp.ui.mainActvity.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonx.roomapp.R
import com.moonx.roomapp.models.Note

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>(), NoteViewHolder.NoteListener {
    var noteList = emptyList<Note>()
    lateinit var listener: NoteReListener

    fun listener(listener: NoteReListener) {
        this.listener = listener
    }

    interface NoteReListener {
        fun onRequsetEditMode(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val holder = NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_note, parent, false)
        )
        holder.listener = this

        return holder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.set(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onEditMode(note: Note) {
        listener.onRequsetEditMode(note)
    }
}