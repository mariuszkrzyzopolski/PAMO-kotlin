package com.example.pamo.ui.dashboard

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
import com.example.pamo.databinding.FragmentDashboardBinding
import java.text.NumberFormat

class DashboardFragment : Fragment() {
    private var weight = 0.0 // weight entered by the user
    private var height = 0.0 // height entered by the user
    private val sex: String? = null
    private var age = 0
    private var weightTextView // shows formatted weight
            : TextView? = null
    private var heightTextView // shows formatted weight
            : TextView? = null
    private var resultTextView // shows calculated BMI
            : TextView? = null
    private var sexTextView: TextView? = null
    private var ageTextView: TextView? = null
    private var calculateButton: Button? = null
    private var binding: FragmentDashboardBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val dashboardViewModel: DashboardViewModel =
            ViewModelProvider(this).get<DashboardViewModel>(
                DashboardViewModel::class.java
            )
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        weightTextView = binding!!.weightTextView
        heightTextView = binding!!.heightTextView
        resultTextView = binding!!.resultTextView
        sexTextView = binding!!.sexEditView
        ageTextView = binding!!.ageTextView
        calculateButton = binding!!.btn
        calculateButton!!.setOnClickListener(calculateOnClick)
        val weightEditText: EditText = binding!!.weightEditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText: EditText = binding!!.heightEditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        val sexEditText: EditText = binding!!.sexEditText
        sexEditText.addTextChangedListener(sexEditTextWatcher)
        val ageEditText: EditText = binding!!.ageEditText
        ageEditText.addTextChangedListener(ageEditTextWatcher)
        dashboardViewModel.text.observe(viewLifecycleOwner) { text: String? ->
            resultTextView!!.setText(
                text
            )
        }
        return root
    }

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
    private val sexEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            sexTextView?.setText(s)
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int, after: Int
        ) {
        }
    }
    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                age = s.toString().toInt()
                ageTextView?.setText(numberFormat.format(age.toLong()))
            } catch (e: NumberFormatException) { // if s is empty or non-numeric
                ageTextView?.setText("")
                age = 0
            }
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }
    private val calculateOnClick = View.OnClickListener {
        var result = 0.0
        result = if (sex == "m") {
            66.5 + 13.75 * weight + 5.003 * height - 6.775 * age
        } else {
            655.1 + 9.563 * weight + 1.85 * height - 4.676 * age
        }
        resultTextView?.setText(String.format("%.2f", result))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private val numberFormat = NumberFormat.getNumberInstance()
    }
}