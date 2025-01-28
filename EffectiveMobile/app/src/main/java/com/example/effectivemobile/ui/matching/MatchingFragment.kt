package com.example.effectivemobile.ui.matching

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentMatchingBinding
import com.example.effectivemobile.ui.matching.adapter.VacancyMatchingAdapter
import kotlinx.coroutines.launch


class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!
    private val matchingAdapter = VacancyMatchingAdapter()

    private val viewModel: MatchingViewModel by viewModels { MatchingViewModelFactory() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerVacanciesView.adapter = matchingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    val data = viewModel.getVacancy()
                    matchingAdapter.submitList(data)
                    Log.d("Vacancy", "Получены данные: $data")
                }
            }
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_MatchingFragment_to_SearchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}