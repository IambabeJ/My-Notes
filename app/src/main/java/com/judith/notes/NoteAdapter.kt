package com.judith.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.judith.notes.databinding.NoteItemBinding
import com.judith.notes.models.Notes


class NoteAdapter( private val notes: List<Notes>, val clicker: (Notes) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Notes) {
            binding.apply {
                binding.idDisplay.text = note.id.toString()
                binding.titleName.text = note.title
                root.setOnClickListener { clicker(note) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context))
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
    interface onNoteItemClickListener{
        fun onClick(note: Notes)
    }
}