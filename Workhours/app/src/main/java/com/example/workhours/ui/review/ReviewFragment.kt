package com.example.workhours.ui.review

import ReviewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.example.workhours.App
import com.example.workhours.R
import com.example.workhours.databinding.FragmentReviewBinding
import com.example.workhours.room.MonthlySummaryDao
import com.example.workhours.room.ShiftDao
import com.example.workhours.ui.repository.MainRepository


class ReviewFragment : Fragment() {


    private var application: App? = null
    private var shiftDao: ShiftDao? = null
    private var monthlySummaryDao: MonthlySummaryDao? = null
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ReviewViewModel by viewModels {
        ReviewViewModelFactory(MainRepository(monthlySummaryDao!!, shiftDao!!))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(layoutInflater)
        application = requireActivity().application as App
        shiftDao = application!!.db.dateTimeUserDao()
        monthlySummaryDao = application!!.db.monthlySummaryDao()

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialTextView.setOnClickListener {



        }

        binding.calendarRecyclerView.adapter = ReviewAdapter(
            resources.getStringArray(R.array.months),
            viewModel.monthlySummaries,
            viewLifecycleOwner
        )

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}



