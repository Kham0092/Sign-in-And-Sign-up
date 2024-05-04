package com.example.firebaseproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseproject.databinding.ActivityImagePageBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.storage

class ImagePage : AppCompatActivity() {
    private lateinit var binding: ActivityImagePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvImage.adapter = ImageAdapter(this, arrayListOf(imageModel("", R.drawable.img)))
        binding.rvImage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.btnAddImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type= "image/*"
            imageLauncher.launch(intent)
        }
    }
    val imageLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== Activity.RESULT_OK){
            if(it.data!=null){
                val reference = com.google.firebase.ktx.Firebase.storage.reference.child("photo")
                reference.putFile(it.data!!.data!!).addOnSuccessListener {
                    reference.downloadUrl.addOnSuccessListener {

                    }
                }
            }
        }
    }
}