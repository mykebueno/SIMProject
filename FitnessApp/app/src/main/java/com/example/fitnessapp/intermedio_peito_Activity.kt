package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class intermedio_peito_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermedio_peito)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}