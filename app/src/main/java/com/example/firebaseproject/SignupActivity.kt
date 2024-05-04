package com.example.firebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaseproject.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.btnSignin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val userName = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()
            val repass = binding.etRepass.text.toString()
            if(email.isBlank() || userName.isBlank() || pass.isBlank() || repass.isBlank()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(pass!=repass)
                {
                    Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){task->
                        if(task.isSuccessful)
                        {
                            Toast.makeText(this,"Registration Succesfull",Toast.LENGTH_SHORT).show()
                            Log.d("khan","success")
                            startActivity(Intent(this,LoginActivity::class.java))
                        }
                        else
                        {
                            Toast.makeText(this,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                            Log.d("khan","failed")
                        }
                    }
                }
            }
        }
    }
}