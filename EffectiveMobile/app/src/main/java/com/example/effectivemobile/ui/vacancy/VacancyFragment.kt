package com.example.effectivemobile.ui.vacancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.databinding.FragmentVacancyBinding
import com.example.effectivemobile.ui.modalSheet.ModalBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class VacancyFragment : Fragment() {

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()

        }
        binding.respondButton.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(parentFragmentManager, modalBottomSheet.tag)
            modalBottomSheet.dialog?.setOnShowListener {
                val modalBottomSheetBehavior =
                    (modalBottomSheet.dialog as BottomSheetDialog).behavior
                modalBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
                modalBottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }


//        // Получаем данные из аргументов фрагмента
//        arguments?.let { args ->
//            val jobTitle = args.getString("jobTitle")
//            val salary = args.getString("salary")
//            val experience = args.getString("experience")
//            val applicants = args.getString("applicants")
//            val activeViews = args.getString("activeViews")
//            val companyName = args.getString("companyName")
//            val address = args.getString("address")
//            val description = args.getString("description")
//            val tasksHeader = args.getString("tasksHeader")
//            val tasksList = args.getStringArray("tasksList")?.joinToString("\n") // Преобразуем массив в строку с переносами строк
//
//            bindData(jobTitle, salary, experience, applicants, activeViews, companyName, address, description, tasksHeader, tasksList)
//        }
    }

//    private fun bindData(
//        jobTitle: String?, salary: String?, experience: String?, applicants: String?, activeViews: String?,
//        companyName: String?, address: String?, description: String?, tasksHeader: String?, tasksList: String?
//    ) {
//        binding.apply {
//            textViewJobTitle.text = jobTitle
//            textViewSalary.text = salary
//            textViewExperience.text = experience
//
//            textViewActiveViews.text = activeViews
//            textViewCompanyName.text = companyName
//            textViewAddress.text = address
//            textViewDescription.text = description
//            textViewTasksHeader.text = tasksHeader
//            textViewTasksList.text = tasksList
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}