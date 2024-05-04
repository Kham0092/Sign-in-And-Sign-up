package com.example.firebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseproject.databinding.ActivityAddNoteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var databaseReference : DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if(title.isBlank() || description.isBlank())
            {
                Toast.makeText(this,"Please enter valid title and description",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val currentUser = auth.currentUser
                currentUser?.let {user->
                    //generates unique key for the note
                    val noteKey = databaseReference
                        .child("users").child(user.uid).child("notes").push().key
                    val noteItem = NoteItem(title,description,noteKey)
                    if(noteKey!=null)
                    {
                        //add notes to the user note
                        databaseReference.child("users").child(user.uid).child("notes")
                            .child(noteKey).setValue(noteItem).addOnCompleteListener {task->
                            if(task.isSuccessful)
                            {
                                Toast.makeText(this@AddNote,"NOTE SAVED SUCESSFULLY",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                                else
                            {
                                Toast.makeText(this@AddNote,"FAILED TO SAVE NOTE",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}