package com.yashendra.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotesRecyclerViewAdapter.InotesRVAdapterHandling {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRecyclerViewAdapter(this, this)
        recyclerview.adapter=adapter

        noteViewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        noteViewModel.allnotes.observe(this, Observer {
            list->
            list?.let {
                adapter.updatelist(it)
            }

        })

    }

    override fun onitemclicked(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun SubmitData(view: View) {
        val noteText=input.text.toString()
        if (noteText.isNotEmpty())
        {
            noteViewModel.insertNote(Note(noteText))
            Toast.makeText(this, "${noteText} Inserted", Toast.LENGTH_SHORT).show()
            input.text.clear()
        }
    }
}