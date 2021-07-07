package com.judith.notes.models

import android.provider.ContactsContract
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao

interface NoteDAO {
    @Query("SELECT * FROM notes")
    fun getAllNotes() : List<Notes>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): Notes

    @Insert
    fun addNote(notes: Notes)

    @Delete
    fun deleteNote(notes: Notes)
}