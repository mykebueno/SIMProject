package com.example.fitnessapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.fitnessapp.R
import com.example.fitnessapp.SharedViewModel
import com.example.fitnessapp.database.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var userMain: User? = null
    private var param1: String? = null
    private var param2: String? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_home, container, false)

        var helloUser = v.findViewById<TextView>(R.id.textViewGreet)
        helloUser.setText("Hi " + userMain?.username + "!")

        val rightNow = Calendar.getInstance()
        val month_date = SimpleDateFormat("EEE dd MMM")
        val month_name: String = month_date.format(rightNow.getTime())
        var date = v.findViewById<TextView>(R.id.textViewDate)
        date.text = month_name

        updateMetrics()

        var stepsText = view?.findViewById<TextView>(R.id.textViewStepsValue)
        var stepsProgressBar = view?.findViewById<ProgressBar>(R.id.progressBarSteps)

        sharedViewModel.sensorModel.currentSteps.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (stepsText != null) {
                stepsText.text = ""+ it
            }

            if (stepsProgressBar != null) {
                if(stepsProgressBar.progress <= stepsProgressBar.max) {
                    stepsProgressBar.progress = it
                }
            }

            sharedViewModel.incValor()
        })

        sharedViewModel.valorInteiro.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (stepsText != null) {
                stepsText.text = "" + it
            }
        })

        return v
    }

    private fun updateMetrics()
    {
        var myDatabase: MyDatabase? = view?.let { MyDatabase.build(it.context) }

        var info: Information? = userMain!!.userId?.let { myDatabase?.DAO()?.getInformation(it) }

        var waters: List<Water>? = userMain?.userId?.let { myDatabase?.DAO()?.getWatersWithUserId(it) }

        var waterValue: Long = 0

        var rightNow = Calendar.getInstance()
        var date = SimpleDateFormat("yyyy-MM-dd")
        var dateToday = date.format(rightNow.time)

        if(waters != null)
        {
            waters.forEach {
                if(dateToday == date.format(it.date))
                {
                    waterValue += it.water!!
                }
            }

            var waterValueText = view?.findViewById<TextView>(R.id.textViewWaterValue)

            if (waterValueText != null) {
                waterValueText.text = waterValue.toString()
            }
        }

        var weights: List<Weight>? = userMain?.userId?.let { myDatabase?.DAO()?.getWeightWithUserId(it) }

        var weightValue: Long = 0

        if(weights != null)
        {
            weights.forEach {
                if(dateToday == date.format(it.date))
                {
                    weightValue = it.weight!!
                }
            }

            var weightValueText = view?.findViewById<TextView>(R.id.textViewKgValue)

            if (weightValueText != null) {
                weightValueText.text = weightValue.toString()
            }
        }

        var calories: List<Calories>? = userMain?.userId?.let { myDatabase?.DAO()?.getCaloriesWithUserId(it) }
        var progressBarCalorie = view?.findViewById<ProgressBar>(R.id.progressBarCalories)
        var caloriesValue: Long = 0

        if (info != null) {
            if (progressBarCalorie != null) {
                progressBarCalorie.max = (info.caloriesIntake)?.toInt()!!
            }
        }

        if(calories != null)
        {
            calories.forEach {
                if(dateToday == date.format(it.date))
                {
                    caloriesValue += it.calories!!
                }
            }

            var caloriesValueText = view?.findViewById<TextView>(R.id.textViewCaloriesValue)

            if (caloriesValueText != null) {
                caloriesValueText.text = caloriesValue.toString()
            }

            if (progressBarCalorie != null) {
                progressBarCalorie.progress = caloriesValue.toInt()
            }
        }
    }

    override fun onDestroy() {
        var myDatabase: MyDatabase? = view?.let { MyDatabase.build(it.context) }

        var stepsInDatabase: List<Steps>? = userMain?.userId?.let { myDatabase?.DAO()?.getStepsWithUserId(it) }

        var todayStepsFound: Boolean = false

        if(stepsInDatabase != null)
        {
            var rightNow = Calendar.getInstance()
            var date = SimpleDateFormat("yyyy-MM-dd")
            var dateToday = date.format(rightNow.time)

            Log.d("Steps", "List of Steps not null!")

            stepsInDatabase.forEach {
                if(dateToday == date.format(it.date))
                {
                    it.date = Calendar.getInstance().time
                    it.steps = sharedViewModel.sensorModel.currentSteps.value
                    if(myDatabase != null)
                    {
                        myDatabase.DAO().updateSteps(it)
                    }
                    Log.d("Steps", "Updated Steps")
                    todayStepsFound = true
                }
            }
        }

        if(!todayStepsFound)
        {
            var steps: Steps = Steps(date = Calendar.getInstance().time, userId = userMain?.userId , steps = sharedViewModel.sensorModel.currentSteps.value)

            Log.d("Steps", "Inserted New Value Steps: " + steps.steps + "with user ID: " + steps.userId)

            if (myDatabase != null) {
                myDatabase.DAO().insertSteps(steps)
            }
        }

        Log.d("Estado do HomeFragment", "Destruido!!")
        super.onDestroy()
    }

    override fun onResume() {

        updateMetrics()
        sharedViewModel.sensorModel.setRunningState(true)
        super.onResume()
    }

    public fun setUser(userFromActivity: User)
    {
        userMain = userFromActivity
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}