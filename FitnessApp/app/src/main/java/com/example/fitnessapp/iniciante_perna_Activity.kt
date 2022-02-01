package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class iniciante_perna_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciante_perna)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}