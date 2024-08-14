package com.example.workhours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workhours.databinding.ModalBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
    private var _binding: ModalBottomSheetContentBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.numPicker.maxValue = 2036
        binding.numPicker.minValue = 2019
        binding.saveButtonAgeFilter.setOnClickListener {
            App.INSTANCE.saveToPreferences(App.KEK_NAME, binding.numPicker.value)

        }


    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}