package com.moonx.roomapp.ui.mainActvity.fragments.edit

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.moonx.roomapp.R
import com.moonx.roomapp.view_models.EditViewModel

class EditFragment : Fragment() {
    private val args by navArgs<EditFragmentArgs>()

    lateinit var mainView: View
    lateinit var viewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = inflater.inflate(R.layout.fragment_edit, container, false)
        initViewModel()
        return mainView
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[EditViewModel::class.java]
        initModelView()
    }

    lateinit var title_et: EditText
    lateinit var content_et: EditText
    lateinit var delete_note: ImageView
    private fun initModelView() {
        title_et = mainView.findViewById(R.id.title)
        content_et = mainView.findViewById(R.id.content)
        delete_note = mainView.findViewById(R.id.delete_note)

        if (args.isFragInAddMode)
            delete_note.visibility = View.GONE
        else {
            with(args.note) {
                title_et.setText(title)
                content_et.setText(content)
            }
        }

        initEvents()
    }

    private fun initEvents() {
        delete_note.setOnClickListener {
            AlertDialog
                .Builder(requireContext())
                .setTitle("Delete note")
                .setMessage("Are you sure you want to delete the note")
                .setCancelable(true)
                .setPositiveButton("Yes") { _, _ ->
                    deleteNote()
                }.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }.create().show()
        }
    }

    private fun deleteNote() {
        findNavController().popBackStack()
        content_et.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()

        val note = args.note.apply {
            title = title_et.text.toString()
            content = content_et.text.toString()
        }
        val itsNotEmpty = content_et.text.toString().trim().isNotEmpty()
        val isAddable = args.isFragInAddMode && itsNotEmpty

        when {
            isAddable -> viewModel.addNote(note)
            itsNotEmpty -> viewModel.editNote(note)
            else -> viewModel.deleteNote(note)
        }

    }
}