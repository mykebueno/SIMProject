package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.fitnessapp.database.Calories
import com.example.fitnessapp.database.MyDatabase
import com.example.fitnessapp.database.User
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class CaloriesChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_chart)

        /*
        val queue = Volley.newRequestQueue(applicationContext)

        var url = "http://127.0.0.1:3000/calories"

        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                showCaloriesRequest(response)
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, "Could not get calories request", Toast.LENGTH_SHORT)
            })

        queue.add(stringReq)
         */

        var myDatabase: MyDatabase = MyDatabase.build(applicationContext)

        val userFound = intent.extras?.get("user") as User

        /*codigo de testeeeee
        var calendarTeste: Calendar = Calendar.getInstance()

        calendarTeste.add(Calendar.DAY_OF_YEAR, -3)

        var calories2: Calories = Calories(calories = 400, date=calendarTeste.time, userId = userFound?.userId)

        myDatabase.DAO().insertCalories(calories2)

        //codigo de testeeee*/

        var calories: List<Calories>? = userFound.userId?.let { myDatabase.DAO().getCaloriesWithUserId(it) }

        var date = SimpleDateFormat("yyyy-MM-dd")

        var hashMapValores : HashMap<String, Int> = HashMap<String, Int> ()
        var hashMapOrdem : HashMap<Int, String> = HashMap<Int, String> ()

        // criar o calendario
        var calendar: Calendar = Calendar.getInstance()

        //  construir os hashmaps
        for(num in 1..7)
        {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            hashMapValores.put(date.format(calendar.time).toString(), 0)
            hashMapOrdem.put(num, date.format(calendar.time).toString())
        }

        if(calories != null)
        {
            calories.forEach{
                var caloriesDate = date.format(it.date).toString()

                if(hashMapValores.containsKey(caloriesDate))
                {
                    var caloriesTotalDay : Int? = hashMapValores.get(caloriesDate)

                    if (caloriesTotalDay != null) {
                        caloriesTotalDay += it.calories?.toInt()!!
                        hashMapValores[caloriesDate] = caloriesTotalDay
                    }
                }
            }
        }

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)

        //var arraylist=ArrayList<ArrayList<Int>>()

        var arr: Array<String?> = arrayOf("0", hashMapOrdem[7], hashMapOrdem[6], hashMapOrdem[5], hashMapOrdem[4], hashMapOrdem[3], hashMapOrdem[2], hashMapOrdem[1])

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Calories")
            .subtitle("Last 7 days")
            .yAxisLabelsEnabled(true)
            .xAxisLabelsEnabled((true))
            .yAxisTitle("cal")
            .backgroundColor("#FFFFFF")
            .categories(arr as Array<String>)
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Calories Value")
                    .data(arrayOf(arrayOf(1, hashMapValores[hashMapOrdem[7]]), arrayOf(2, hashMapValores[hashMapOrdem[6]]), arrayOf(3, hashMapValores[hashMapOrdem[5]]),
                        arrayOf(4, hashMapValores[hashMapOrdem[4]]), arrayOf(5, hashMapValores[hashMapOrdem[3]]), arrayOf(6, hashMapValores[hashMapOrdem[2]]), arrayOf(7, hashMapValores[hashMapOrdem[1]])))
            )
            )

        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }

    fun showCaloriesRequest(jsonCalories: String)
    {
        // vamos guard como objeto de json
        val arrayObjJson = JSONObject(jsonCalories)

        // vamos guardar como array de objetos de json
        val arr = JSONArray(arrayObjJson)

        for (item in 0 until arr.length())
        {
            var jsonObject = arr.getJSONObject(item)
            Log.e("Calories", jsonObject.getString("calories"))
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}