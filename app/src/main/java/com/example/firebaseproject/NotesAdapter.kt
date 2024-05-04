package com.example.firebaseproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.databinding.NotesitemBinding

class NotesAdapter(private val notes : ArrayList<NoteItem>,val context : Context,val listener : OnItemClickListener) : RecyclerView.Adapter<NotesAdapter.myViewHolder>() {
    inner class myViewHolder(val binding: NotesitemBinding) : RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        init {
            binding.btnDelete.setOnClickListener(this)
            binding.btnUpdate.setOnClickListener(this)
            }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                if (v != null) {
                    if(v.id==R.id.btnDelete)
                        listener.onDeleteClickListner(position,notes)
                    else
                        listener.onUpdateClickListener(position,notes)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = NotesitemBinding.inflate(LayoutInflater.from(context),parent,false)
        return myViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.count()
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.binding.tvTitleR.text = notes[position].title
        holder.binding.tvDescriptionR.text = notes[position].description
    }
    interface OnItemClickListener{
        fun onDeleteClickListner(positon:Int,notes : ArrayList<NoteItem>)
        fun onUpdateClickListener(positon:Int,notes : ArrayList<NoteItem>)
    }
}