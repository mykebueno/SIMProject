package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Intermedio_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intermedia)
    }

    fun treinoBraco2(view: View)
    {
        val intent = Intent(this, intermedio_braco_Activity::class.java)
        startActivity(intent)
    }

    fun treinoCostas2(view: View)
    {
        val intent = Intent(this, intermedio_costas_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPeito2(view: View)
    {
        val intent = Intent(this, intermedio_peito_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPerna2(view: View)
    {
        val intent = Intent(this, intermedio_perna_Activity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}