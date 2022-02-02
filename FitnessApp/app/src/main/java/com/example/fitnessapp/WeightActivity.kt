package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.fitnessapp.database.MyDatabase
import com.example.fitnessapp.database.User
import com.example.fitnessapp.database.Weight
import java.text.SimpleDateFormat
import java.util.*

class WeightActivity : AppCompatActivity() {

    var userMain: User? = null

    var todayInputWeight: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        userMain = intent.extras?.get("user") as User

        var alertWeight = findViewById<TextView>(R.id.textViewAlertWeight)

        var myDatabase: MyDatabase = MyDatabase.build(applicationContext)

        var weights: List<Weight> = userMain!!.userId?.let { myDatabase.DAO().getWeightWithUserId(it) }!!

        if(weights != null)
        {
            // queremos mostrar a informaçao caso ja tenhamos uma quantidade de agua adicionada
            var rightNow = Calendar.getInstance()
            var date = SimpleDateFormat("yyyy-MM-dd")
            var dateToday = date.format(rightNow.time)


            weights.forEach {
                if(dateToday == date.format(it.date))
                {
                    //vamos avisar que ja introduziu o peso hoje!
                    alertWeight.visibility = View.VISIBLE
                    todayInputWeight = true
                }
            }
        }
    }

    fun submitWeight(view: View)
    {
        var weightValueString = findViewById<EditText>(R.id.editTextNumberCaloriesIntake).text.toString()
        var alertWeight = findViewById<TextView>(R.id.textViewAlertWeight)

        var weightValue = weightValueString.toLong()

        if(weightValueString.equals(""))
        {
            return
        }

        var myDatabase: MyDatabase = MyDatabase.build(applicationContext)

        if(todayInputWeight)
        {
            var weights: List<Weight> = userMain!!.userId?.let { myDatabase.DAO().getWeightWithUserId(it) }!!

            if(weights != null)
            {
                // queremos mostrar a informaçao caso ja tenhamos uma quantidade de agua adicionada
                var rightNow = Calendar.getInstance()
                var date = SimpleDateFormat("yyyy-MM-dd")
                var dateToday = date.format(rightNow.time)

                weights.forEach {
                    if(dateToday == date.format(it.date))
                    {
                        it.date = Calendar.getInstance().time
                        it.weight = weightValue
                        myDatabase.DAO().updateWeight(it)
                        Log.d("Weight", "Updated Weight")
                    }
                }
            }
        }
        else
        {
            var weight: Weight = Weight(weight = weightValue, date=Calendar.getInstance().time, userId = userMain?.userId)

            Log.d("Weight", "Inserted New Value Weight: " + weight.weight.toString())

            myDatabase.DAO().insertWeights(weight)
            alertWeight.visibility = View.VISIBLE
            todayInputWeight = true
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}