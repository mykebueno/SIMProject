package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class intermedio_braco_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermedio_braco)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}