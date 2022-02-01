package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Iniciante_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciante)
    }

    fun treinoBraco(view: View)
    {
        val intent = Intent(this, iniciante_braco_Activity::class.java)
        startActivity(intent)
    }

    fun treinoCostas(view: View)
    {
        val intent = Intent(this, iniciante_costas_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPeito(view: View)
    {
        val intent = Intent(this, iniciante_peito_Activity::class.java)
        startActivity(intent)
    }

    fun treinoPerna(view: View)
    {
        val intent = Intent(this, iniciante_perna_Activity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}