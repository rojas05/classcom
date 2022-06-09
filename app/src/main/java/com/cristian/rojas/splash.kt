package com.cristian.rojas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        starTimer()
    }
    fun starTimer() {
        object: CountDownTimer(1500,1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, MainActivity::class.java).apply { }
                startActivity(intent)
                finish()
            }

        }.start()

    }
}