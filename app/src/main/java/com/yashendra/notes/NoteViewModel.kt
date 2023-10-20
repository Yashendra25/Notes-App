package com.yashendra.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application) {
    val allnotes:LiveData<List<Note>>
    val repository:NoteRepository
    init {
        val dao=Notedatabase.getDatabase(application).getNoteDao()
        repository=NoteRepository(dao)
        allnotes=repository.allNotes
    }
    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

}