package com.example.effectivemobile.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.effectivemobile.app.App
import com.example.effectivemobile.databinding.FragmentFavouritesBinding
import com.example.effectivemobile.ui.favourites.adapter.VacancyFavoritesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    private val viewModel: FavouritesViewModel by viewModels { viewModelFactory }
    private val vacancyAdapter = VacancyFavoritesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFavoritesView.adapter = vacancyAdapter
        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.getVacancy()
                }
                launch {
//                    viewModel.insertItemToDatabase()
                }
            }
        }

        viewModel.vacancycies.observe(viewLifecycleOwner) { vacancies ->
            vacancyAdapter.submitList(vacancies)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}