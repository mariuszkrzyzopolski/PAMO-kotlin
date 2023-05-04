package com.example.pamo.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.pamo.R
import com.example.pamo.databinding.FragmentMainBinding
import java.text.NumberFormat

class MainActivity : Fragment() {
    private var weight = 0.0 // weight entered by the user
    private var height = 0.0 // height entered by the user
    private var weightTextView // shows formatted weight
            : TextView? = null
    private var heightTextView // shows formatted weight
            : TextView? = null
    private var resultTextView // shows calculated BMI
            : TextView? = null
    private var calculateButton: Button? = null
    private var chartButton: Button? = null
    private var binding: FragmentMainBinding? = null

    // called when the activity is first created
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val mainViewModel: MainViemModel = ViewModelProvider(this).get<MainViemModel>(
            MainViemModel::class.java
        )
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        // get references to programmatically manipulated TextViews
        weightTextView = binding!!.weightTextView
        heightTextView = binding!!.heightTextView
        resultTextView = binding!!.resultTextView
        calculateButton = binding!!.btn
        calculateButton!!.setOnClickListener(calculateOnClick)
        chartButton = binding!!.wykresy
        chartButton!!.setOnClickListener(chartOnClick)
        val weightEditText: EditText = binding!!.weightEditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText: EditText = binding!!.heightEditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        return root
    }

    // listener object for the EditText's text-changed events
    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            try {
                weight = s.toString().toDouble()
                weightTextView?.setText(numberFormat.format(weight))
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                weightTextView?.setText("")
                weight = 0.0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }

    // listener object for the EditText's text-changed events
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = s.toString().toDouble()
                heightTextView?.setText(numberFormat.format(height))
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                heightTextView?.setText("")
                height = 0.00
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }
    private val calculateOnClick = View.OnClickListener {
        val BMI = weight / Math.pow(height / 100, 2.0)
        resultTextView?.setText(String.format("%.2f", BMI))
    }
    private val chartOnClick = View.OnClickListener {
        val navController: NavController? =
            activity?.let { it1 -> findNavController(it1, R.id.nav_host_fragment_activity_navigation) }
        if (navController != null) {
            navController.navigate(R.id.action_navigation_bmi_to_chartFragment2)
        }
    }

    companion object {
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}