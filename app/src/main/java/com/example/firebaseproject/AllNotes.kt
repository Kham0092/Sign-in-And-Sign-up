package com.example.firebaseproject
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject.databinding.ActivityAllNotesBinding
import com.example.firebaseproject.databinding.DialogUpdateNotesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotes : AppCompatActivity(),NotesAdapter.OnItemClickListener {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAllNotesBinding
    val noteList = ArrayList<NoteItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        loadData()
    }

    override fun onDeleteClickListner(positon: Int, notes: ArrayList<NoteItem>) {
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
        val currentUser = auth.currentUser
        val id = notes[positon].noteId
        if (currentUser != null) {
            if (id != null) {
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
                databaseReference.child("users").child(currentUser.uid).child("notes").child(id).removeValue()
            }
        }
    }

    override fun onUpdateClickListener(positon: Int, notes: ArrayList<NoteItem>) {
        val noteId = notes[positon].noteId
        var newTitle : String
        var newDescription : String
        val bindingDialog:DialogUpdateNotesBinding =   DialogUpdateNotesBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this).setView(bindingDialog.root).create()
        dialog.show()
        bindingDialog.btnSave.setOnClickListener {
            newTitle = bindingDialog.updateTitle.text.toString()
            newDescription = bindingDialog.updateDescription.text.toString()
            updateNoteDatabase(noteId,newTitle,newDescription)
            dialog.dismiss()
        }
        bindingDialog.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun updateNoteDatabase(noteId: String?, newTitle: String, newDescription: String) {
        val currentUser = auth.currentUser
        currentUser?.let { user->
            val id = user.uid
            val update = NoteItem(newTitle,newDescription,noteId)
            if(noteId!=null){
                databaseReference.child("users").child(id).child("notes")
                    .child(noteId).setValue(update)
                .addOnCompleteListener {task->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(this,"Update Successfull",Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this,"Update Failed",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    fun loadData(){
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val noteReference = databaseReference.child("users").child(user.uid)
                .child("notes")
            noteReference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    noteList.clear()
                    for (mySnapshot in snapshot.children){
                        val note = mySnapshot.getValue(NoteItem::class.java)
                        note?.let {
                            noteList.add(note)
                        }
                    }
                    noteList.reverse()
                    val adapter = NotesAdapter(noteList,this@AllNotes,this@AllNotes)
                    binding.rvNotes.layoutManager = LinearLayoutManager(this@AllNotes,LinearLayoutManager.VERTICAL,false)
                    binding.rvNotes.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}