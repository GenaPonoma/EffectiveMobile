package com.example.effectivemobile.ui.search


import android.os.Bundle
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
import com.example.effectivemobile.databinding.FragmentSearchBinding
import com.example.effectivemobile.ui.search.adapter.OfferAdapter
import com.example.effectivemobile.ui.search.adapter.VacancyAdapter
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val offerAdapter = OfferAdapter()
    private val vacancyAdapter = VacancyAdapter { itemId -> viewModel.saveItemToDatabase(itemId) }
    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerSearchView.adapter = offerAdapter
        binding.recyclerVacanciesView.adapter = vacancyAdapter
        observeData()
        binding.nextToMatching.setOnClickListener {
            findNavController().navigate(R.id.action_NavigationSearch_to_MatchingFragment)
        }
    }
    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getOffers()
                }
                launch {
                    viewModel.getVacancy()
                }
            }
        }
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            offerAdapter.submitList(offers)
        }
        viewModel.vacancycies.observe(viewLifecycleOwner) { vacancies ->
            val remainingCount = vacancies.size
            binding.nextToMatching.text = resources.getQuantityString(
                R.plurals.count_vacancy,
                remainingCount,
                remainingCount
            )
            if (vacancies.size > 3) {
                vacancyAdapter.submitList(vacancies.subList(0, 3))
            } else {
                vacancyAdapter.submitList(vacancies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}