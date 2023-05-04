package com.example.pamo.ui.chart

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class ChartViewModel : ViewModel() {
    private var xAxis: ArrayList<*>? = null

    init {
        setDataSet()
    }

    fun setDataSet(): List<BarDataSet>? {
        xAxis = ArrayList<Any?>()
        (xAxis as ArrayList<Any?>).add("JAN")
        (xAxis as ArrayList<Any?>).add("FEB")
        (xAxis as ArrayList<Any?>).add("MAR")
        (xAxis as ArrayList<Any?>).add("APR")
        (xAxis as ArrayList<Any?>).add("MAY")
        (xAxis as ArrayList<Any?>).add("JUN")
        val valueSet1: ArrayList<BarEntry> = ArrayList()
        val v1e1 = BarEntry(0.0f, 19.1f) // Jan
        valueSet1.add(v1e1)
        val v1e2 = BarEntry(1.0f, 19.2f) // Feb
        valueSet1.add(v1e2)
        val v1e3 = BarEntry(2.0f, 19.5f) // Mar
        valueSet1.add(v1e3)
        val v1e4 = BarEntry(3.0f, 20.1f) // Apr
        valueSet1.add(v1e4)
        val v1e5 = BarEntry(4.0f, 19.6f) // May
        valueSet1.add(v1e5)
        val v1e6 = BarEntry(5.0f, 20.2f) // Jun
        valueSet1.add(v1e6)
        val barDataSet1 = BarDataSet(valueSet1, "BMI")
        barDataSet1.color = Color.rgb(0, 155, 0)
        var dataSet: List<BarDataSet>? = null
        dataSet?.toMutableList()?.add(barDataSet1)
        return dataSet
    }
}