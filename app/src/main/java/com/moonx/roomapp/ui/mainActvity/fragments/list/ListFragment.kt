package com.moonx.roomapp.ui.mainActvity.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moonx.roomapp.R
import com.moonx.roomapp.app.St
import com.moonx.roomapp.models.Note
import com.moonx.roomapp.view_models.ListViewModel

class ListFragment : Fragment(), NoteAdapter.NoteReListener {
    private val args by navArgs<ListFragmentArgs>()
    lateinit var mainView: View
    lateinit var user_name_tv: TextView
    lateinit var add_note: ImageView
    lateinit var add_check_note: ImageView

    lateinit var noteAdapter: NoteAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = inflater.inflate(R.layout.fragment_main, container, false)
        initViewModel()
        return mainView
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        initModelView()
        observe()
    }

    private fun observe() {
        viewModel.getNotes(args.userArgs.id).observe(requireActivity(), {
            noteAdapter.apply {
                noteList = it
                notifyDataSetChanged()
            }
        })
    }

    private fun initModelView() {
        user_name_tv = mainView.findViewById(R.id.user_name_tv)
        user_name_tv.text = args.userArgs.name
        add_note = mainView.findViewById(R.id.add_note)
        add_check_note = mainView.findViewById(R.id.add_check_note)

        noteAdapter = NoteAdapter()
        noteAdapter.listener = this
        recyclerView = mainView.findViewById(R.id.recyclerView)
        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            .apply {
                recyclerView.layoutManager = this
            }
        recyclerView.adapter = noteAdapter

        initEvent()
    }

    private fun initEvent() {
        add_note.setOnClickListener {
            val note = Note(
                0,
                "",
                "",
                St.NOTETYPE_NOTE,
                args.userArgs.id
            )

            val action = ListFragmentDirections
                .actionMainFragmentToEditFragment(isFragInAddMode =  true, note = note)

            findNavController().navigate(action)
        }

        add_check_note.setOnClickListener {

            val note = Note(
                0,
                "",
                "",
                St.NOTETYPE_NOTE,
                args.userArgs.id
            )

            val action = ListFragmentDirections
                .actionMainFragmentToEditFragment(isFragInAddMode =  true, note = note)

            findNavController().navigate(action)
        }

        user_name_tv.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onRequsetEditMode(note: Note) {
        val action = ListFragmentDirections
            .actionMainFragmentToEditFragment(note= note, isFragInAddMode = false)

        findNavController().navigate(action)
    }
}