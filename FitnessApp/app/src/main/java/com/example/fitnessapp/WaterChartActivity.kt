package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fitnessapp.database.Calories
import com.example.fitnessapp.database.MyDatabase
import com.example.fitnessapp.database.User
import com.example.fitnessapp.database.Water
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class WaterChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_chart)

        var myDatabase: MyDatabase = MyDatabase.build(applicationContext)

        val userFound = intent.extras?.get("user") as User

        //codigo de testeeeee
        var calendarTeste: Calendar = Calendar.getInstance()

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        var water2: Water = Water(water = 1600, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 2400, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 2100, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 1400, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 1950, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 2300, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        calendarTeste.add(Calendar.DAY_OF_YEAR, -1)

        water2 = Water(water = 2400, date=Calendar.getInstance().getTime(), userId = userFound?.userId)

        myDatabase.DAO().insertWaters(water2)

        //codigo de testeeee

        var waters: List<Water>? = userFound?.userId?.let { myDatabase.DAO().getWatersWithUserId(it) }

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

        if(waters != null)
        {
            waters.forEach{
                var watersDate = date.format(it.date).toString()

                if(hashMapValores.containsKey(watersDate))
                {
                    var caloriesTotalDay : Int? = hashMapValores.get(watersDate)

                    if (caloriesTotalDay != null) {
                        caloriesTotalDay += it.water?.toInt()!!
                        hashMapValores[watersDate] = caloriesTotalDay
                    }
                }
            }
        }

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)

        //var arraylist=ArrayList<ArrayList<Int>>()

        var arr: Array<String?> = arrayOf("0", hashMapOrdem[7], hashMapOrdem[6], hashMapOrdem[5], hashMapOrdem[4], hashMapOrdem[3], hashMapOrdem[2], hashMapOrdem[1])

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Water")
            .subtitle("Last 7 days")
            .yAxisLabelsEnabled(true)
            .xAxisLabelsEnabled((true))
            .yAxisTitle("ml")
            .backgroundColor("#FFFFFF")
            .categories(arr as Array<String>)
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Water Value")
                    .data(arrayOf(arrayOf(1, hashMapValores[hashMapOrdem[7]]), arrayOf(2, hashMapValores[hashMapOrdem[6]]), arrayOf(3, hashMapValores[hashMapOrdem[5]]),
                        arrayOf(4, hashMapValores[hashMapOrdem[4]]), arrayOf(5, hashMapValores[hashMapOrdem[3]]), arrayOf(6, hashMapValores[hashMapOrdem[2]]), arrayOf(7, hashMapValores[hashMapOrdem[1]])))
            )
            )

        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}