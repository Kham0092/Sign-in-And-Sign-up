package com.example.firebaseproject
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser!=null)
        {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()
            if(userName.isBlank() || pass.isBlank()){
                Toast.makeText(this,"Email or password missing",Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(userName,pass).addOnCompleteListener {task->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(this,"Sign in Sucessfull",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this,"Invalid Email or Password",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
    }
}