package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class avancado_peito_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avancado_peito)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}