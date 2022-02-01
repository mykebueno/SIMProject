package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class iniciante_costas_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciante_costas)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}