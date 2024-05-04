package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  auth = FirebaseAuth.getInstance()
      //  if(auth.currentUser!=null)
      //  {
      //      binding.tv.text = auth.currentUser!!.email.toString()
      //  }
      //  binding.btnLogout.setOnClickListener {
      //      auth.signOut()
      //      startActivity(Intent(this@MainActivity,LoginActivity::class.java))
      //      finish()
      //  }

        binding.btnCreateNode.setOnClickListener {
            startActivity(Intent(this@MainActivity,AddNote::class.java))
        }
        binding.btnOpen.setOnClickListener {
            startActivity(Intent(this@MainActivity,AllNotes::class.java))
        }
        binding.btnImagePage.setOnClickListener {
            startActivity(Intent(this@MainActivity,ImagePage::class.java))
        }
    }
}