package com.example.effectivemobile.ui.responses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobile.databinding.FragmentResponsesBinding


class ResponsesFragment : Fragment() {

    private var _binding: FragmentResponsesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val responsesViewModel =
            ViewModelProvider(this).get(ResponsesViewModel::class.java)

        _binding = FragmentResponsesBinding.inflate(inflater, container, false)
       return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}