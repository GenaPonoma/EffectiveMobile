package com.example.skillbox_hw_quiz

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        processAndDisplayResult()
        animateViews()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_surveyFragment)

        }
    }

    private fun animateViews() {
        ObjectAnimator.ofFloat(binding.resultText, View.ROTATION, 0f, 4f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(binding.backButton, View.TRANSLATION_X, 0f, 44f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    private fun processAndDisplayResult() {
        var les = "";
        val args = arguments?.getString(FEEDBACK_KEY)
        val words = args?.split("\\s+".toRegex())

        words?.forEach { les += getString(it.toInt()); }

        binding.resultText.text = les
    }



    companion object {
        const val FEEDBACK_KEY = "feedback"
    }


}

