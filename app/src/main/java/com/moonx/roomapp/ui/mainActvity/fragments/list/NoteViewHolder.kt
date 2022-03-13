package com.moonx.roomapp.ui.mainActvity.fragments.list

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.moonx.roomapp.R
import com.moonx.roomapp.models.Note

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var root: CardView
    lateinit var note: Note
    lateinit var act_title: TextView
    lateinit var content: TextView

    lateinit var listener: NoteListener

    interface NoteListener {
        fun onEditMode(note: Note)
    }

    fun set(note: Note) {
        this.note = note
        root = itemView.findViewById(R.id.root)
        act_title = itemView.findViewById(R.id.act_title)
        content = itemView.findViewById(R.id.content)

        initView()
    }

    private fun initView() {
        if (note.title.isNotEmpty())
            act_title.text = note.title.trim()
        else {
            act_title.visibility = View.GONE
            content.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
        }

        content.text = note.content.trim()
        initEvent()
    }

    private fun initEvent() {
        root.setOnClickListener {
            listener.onEditMode(note)
        }
    }
}