package com.example.firebaseproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.example.firebaseproject.databinding.ActivityWelcomeScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import kotlin.coroutines.coroutineContext

class WelcomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("khan","start")
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
       CoroutineScope(Dispatchers.IO).launch {
           delay(1000)
           withContext(Dispatchers.Main){
               startActivity(Intent(this@WelcomeScreen,LoginActivity::class.java))
               finish()
           }
       }
        val wt = "Welcome"
        val spanableString = SpannableString(wt)
        spanableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),0,5,0)
        spanableString.setSpan(ForegroundColorSpan(Color.parseColor("#312222")),5,wt.length,0)
        binding.tvWelcome.text = spanableString
        Log.d("khan","end")
    }
}