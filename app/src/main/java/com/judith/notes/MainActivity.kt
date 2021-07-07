
package com.judith.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.judith.notes.databinding.ActivitymainBinding
import com.judith.notes.models.NoteDatabase
import com.judith.notes.models.Notes
import com.judith.notes.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitymainBinding
    private lateinit var database: NoteDatabase
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitymainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //instantiating database
        database = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes_database"
        ).allowMainThreadQueries().build()

        //instantiating viewModel
                viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getNotes(database)

        //observe live data
        viewModel.notesLiveData.observe(this, { notes ->

            noteAdapter = NoteAdapter(database.noteDao().getAllNotes()){

            }
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = noteAdapter
            }
        })
        binding.button.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val content = binding.contentInput.toString()

            saveNote(title, content)
        }
    }

    private fun saveNote(title: String, content: String) {
        val note = Notes(id = 0, title, content)
        database.noteDao().addNote(note)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }


    private val listener = object : NoteAdapter.onNoteItemClickListener {
        override fun onClick(note: Notes) {
            val intent = Intent(this@MainActivity, NoteDetailsActivity::class.java)
            intent.run {
                putExtra("id", note.id)
                startActivity(this)

            }
        }

    }
}
class NoteDetailsActivity