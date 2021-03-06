package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class ChartsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)

        //var arraylist=ArrayList<ArrayList<Int>>()

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .title("Peso")
            .subtitle("Last 7 days")
            .yAxisMax(20F)
            .yAxisLabelsEnabled(true)
            .xAxisLabelsEnabled((true))
            .yAxisTitle("Kg")
            .backgroundColor("#FFFFFF")
            .dataLabelsEnabled(false)
            .series(arrayOf(
                AASeriesElement()
                    .name("Peso")
                    .data(arrayOf(arrayOf(1, 7.0), arrayOf(2, 7.0), arrayOf(3, 7.0), arrayOf(4, 7.0), arrayOf(5, 7.0), arrayOf(6, 7.0), arrayOf(7, 7.0)))
            )
            )

        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }
}