package com.example.pamo.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.pamo.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData

class ChartFragment : Fragment() {
    private var mViewModel: ChartViewModel? = null
    private var barchart: BarChart? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_chart, container, false)
        barchart = BarChart(activity)
        mViewModel = ChartViewModel()
        val chart: FrameLayout = v.findViewById<FrameLayout>(R.id.chartLayout)
        val data = BarData(mViewModel!!.setDataSet())
        data.setBarWidth(0.9f)
        barchart!!.setData(data)
        chart.addView(barchart)
        return v
    }

    companion object {
        fun newInstance(): ChartFragment {
            return ChartFragment()
        }
    }
}