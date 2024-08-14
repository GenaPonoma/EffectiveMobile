package com.example.skillbox_hw_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.skillbox_hw_quiz.databinding.FragmentHelloBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class HelloFragment : Fragment() {

    private val calendar = Calendar.getInstance()
    private var _binding: FragmentHelloBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade_in)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHelloBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_helloFragment_to_surveyFragment)
        }
        binding.selectedDate.setOnClickListener {
            val dateDialog = MaterialDatePicker
                .Builder
                .datePicker()
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeMillis ->
                calendar.timeInMillis = timeMillis;
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH) + 1
                val year = calendar.get(Calendar.YEAR)
                val textBretsday = "$day.$month.$year"

                Snackbar.make(binding.selectedDate, textBretsday, Snackbar.LENGTH_LONG).show()
            }
            dateDialog.show(parentFragmentManager, "date")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}