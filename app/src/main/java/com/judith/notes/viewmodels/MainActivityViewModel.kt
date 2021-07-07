package com.judith.notes.viewmodels

import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.judith.notes.models.NoteDatabase
import com.judith.notes.models.Notes

class MainActivityViewModel :ViewModel() {
    val notesLiveData = MutableLiveData<List<Notes>>()

    init {

    }

    fun getNotes(database: NoteDatabase) {
        notesLiveData.postValue(database.noteDao().getAllNotes())
    }

    fun addNote(database: NoteDatabase, notes: Notes) {
        database.noteDao().addNote(notes)
        getNotes(database)
    }
}