package com.judith.notes.models

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Notes::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDAO
}