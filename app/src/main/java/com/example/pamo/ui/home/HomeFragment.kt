package com.example.pamo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.pamo.R
import com.example.pamo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val homeViewModel: HomeViewModel = ViewModelProvider(this).get<HomeViewModel>(
            HomeViewModel::class.java
        )
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val quizButton: Button = binding!!.quiz
        val root: View = binding!!.getRoot()
        quizButton.setOnClickListener(quizOnClick)
        return root
    }

    private val quizOnClick = View.OnClickListener {
        activity?.let { it1 -> findNavController(it1, R.id.nav_host_fragment_activity_navigation) }
            ?.navigate(R.id.action_navigation_start_to_quizFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}