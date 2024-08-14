package com.example.workhours.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.workhours.App
import com.example.workhours.databinding.FragmentAddBinding
import com.example.workhours.room.MonthlySummaryDao
import com.example.workhours.room.ShiftDao
import com.example.workhours.ui.repository.MainRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private var application: App? = null
    private var monthlySummaryDao: MonthlySummaryDao? = null
    private var shiftDao: ShiftDao? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater)
        application = requireActivity().application as App
        shiftDao = application!!.db.dateTimeUserDao()
        monthlySummaryDao = application!!.db.monthlySummaryDao()
        return binding.root
    }

    private val viewModel: AddViewModel by viewModels {
        AddViewModelFactory(MainRepository(monthlySummaryDao!!, shiftDao!!))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartDateSelectionCard.text =
            this.viewModel.getFormattedDate(viewModel.calendar.timeInMillis)
        binding.buttonEndDateSelectionCard.text =
            this.viewModel.getFormattedDate(viewModel.calendar.timeInMillis)
        viewModel.month = viewModel.getMonthFromDate(viewModel.calendar.timeInMillis)
        binding.buttonStartDateSelectionCard.setOnClickListener {
            viewModel.datePickerStart = viewModel.showDatePicker("") { timeInMillis ->
                binding.buttonStartDateSelectionCard.text = viewModel.getFormattedDate(timeInMillis)
                viewModel.month = viewModel.getMonthFromDate(timeInMillis)
            }
            viewModel.datePickerStart.show(parentFragmentManager, "datetime_start")
        }
        binding.buttonEndDateSelectionCard.setOnClickListener {
            viewModel.datePickerEnd =
                viewModel.showDatePicker("Выбирите дату окончания") { timeInMillis ->
                    binding.buttonEndDateSelectionCard.text =
                        viewModel.getFormattedDate(timeInMillis)
                }
            viewModel.datePickerEnd.show(parentFragmentManager, "datetime_end")
        }
        binding.buttonEndTimeSelectionCard.setOnClickListener {
            viewModel.pickerTimeEnd = viewModel.createTimePicker().apply {
                addOnPositiveButtonClickListener {
                    binding.buttonEndTimeSelectionCard.text =
                        viewModel.getFormattedTime(this.hour, this.minute)
                }
            }
            viewModel.pickerTimeEnd.show(parentFragmentManager, "time_end")
        }
        binding.buttonStartTimeSelectionCard.setOnClickListener {
            viewModel.pickerTimeStart = viewModel.createTimePicker().apply {
                addOnPositiveButtonClickListener {
                    binding.buttonStartTimeSelectionCard.text =
                        viewModel.getFormattedTime(this.hour, this.minute)
                }
            }
            viewModel.pickerTimeStart.show(parentFragmentManager, "time_start")
        }
        binding.upData.setOnClickListener {
            viewModel.addItemBD(
                startDate = binding.buttonStartDateSelectionCard.text.toString(),
                endDate = binding.buttonEndDateSelectionCard.text.toString(),
                startTime = binding.buttonStartTimeSelectionCard.text.toString(),
                endTime = binding.buttonEndTimeSelectionCard.text.toString(),
                month = viewModel.month,
                comments = binding.editTextComments.text.toString()
            )
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Успешно")
                .setMessage("Данные сохранены!")
                .setPositiveButton("Закрыть") { dialog, which ->
                }
                .show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


