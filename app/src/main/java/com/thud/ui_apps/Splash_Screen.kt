package com.thud.ui_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast


class Splash_Screen : AppCompatActivity() {

    private val handler =  Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)


    }
    private val runnable = Runnable {
        if(!isFinishing){
            Toast.makeText(this, "Splash screen", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
