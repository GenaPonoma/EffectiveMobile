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
import com.example.effectivemobile.app.App
import com.example.effectivemobile.databinding.FragmentMatchingBinding
import com.example.effectivemobile.ui.matching.MatchingViewModel
import com.example.effectivemobile.ui.matching.MatchingViewModelFactory
import com.example.effectivemobile.ui.matching.adapter.VacancyMatchingAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchingFragment : Fragment() {

    @Inject
    lateinit var matchingViewModelFactory: MatchingViewModelFactory

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!

    private val matchingAdapter = VacancyMatchingAdapter()
    private val viewModel: MatchingViewModel by viewModels { matchingViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
        setupNavigation()
    }

    private fun setupRecyclerView() {
        binding.recyclerVacanciesView.adapter = matchingAdapter
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    val data = viewModel.getVacancy()
                    matchingAdapter.submitList(data)
                    Log.d("Vacancy", "Получены данные: $data")
                }
            }
        }
    }

    private fun setupNavigation() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_MatchingFragment_to_SearchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}