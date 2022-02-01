package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Avancado_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avancado)
    }

    fun treinoBraco3(view: View)
    {
        val intent = Intent(this, avancado_braco_Activity::class.java)
        startActivity(intent)
    }

    fun treinoCostas3(view: View)
    {
        val intent = Intent(this, avancado_costas_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPeito3(view: View)
    {
        val intent = Intent(this, avancado_peito_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPerna3(view: View)
    {
        val intent = Intent(this, avancado_perna_Activity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}