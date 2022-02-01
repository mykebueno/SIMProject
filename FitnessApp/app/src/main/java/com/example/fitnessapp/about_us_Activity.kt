package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class about_us_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sobre_nos)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}