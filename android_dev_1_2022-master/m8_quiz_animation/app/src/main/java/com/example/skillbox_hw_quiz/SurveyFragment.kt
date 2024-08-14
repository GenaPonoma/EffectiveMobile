package com.example.skillbox_hw_quiz

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentSurveyBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage


class SurveyFragment : Fragment() {
    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = androidx.transition.TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quiz = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        displayQuestions(quiz)

        binding.headerTitleQuestion0.animate().apply {
            duration = 3000
            translationX(23f)
            alpha(0f)
            alpha(0.2f)
            alpha(0.5f)
            alpha(0.8f)
            alpha(0.9f)
            alpha(1f)

            interpolator = AccelerateInterpolator()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_surveyFragment_to_helloFragment)
        }


        binding.buttonSend.setOnClickListener {
            val selectedIndices = getSelectedIndices()
            if (selectedIndices.isNotEmpty()) {
                val feedback = QuizStorage.answer(quiz, selectedIndices)
                val bundle = bundleOf(FEEDBACK_KEY to feedback)



                findNavController().navigate(R.id.action_surveyFragment_to_resultsFragment, bundle)
            } else {
                Toast.makeText(context, R.string.hint_toast, Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }


    private fun displayQuestions(quiz: Quiz) {
        val questionTitles = listOf(
            binding.headerTitleQuestion0,
            binding.headerTitleQuestion1,
            binding.headerTitleQuestion2
        )
        val answerGroups = listOf(
            listOf(binding.rbAnswer1, binding.rbAnswer2, binding.rbAnswer3, binding.rbAnswer4),
            listOf(
                binding.rbAnswer1RadioGroup1,
                binding.rbAnswer2RadioGroup1,
                binding.rbAnswer3RadioGroup1,
                binding.rbAnswer4RadioGroup1
            ),
            listOf(
                binding.rbAnswer1RadioGroup2,
                binding.rbAnswer2RadioGroup2,
                binding.rbAnswer3RadioGroup2,
                binding.rbAnswer4RadioGroup2
            )
        )

        for (i in quiz.questions.indices) {
            val question = quiz.questions[i]
            questionTitles[i].text = question.question

            for (j in question.answers.indices) {
                val answer = question.answers[j]
                answerGroups[i][j].text = answer
            }
        }
    }

    private fun getSelectedIndices(): List<Int> {
        val selectedRadioButtonIds = listOf(
            binding.radioGroup.checkedRadioButtonId,
            binding.radioGroup1.checkedRadioButtonId,
            binding.radioGroup2.checkedRadioButtonId
        )
        val selectedIndices = mutableListOf<Int>()
        for (id in selectedRadioButtonIds) {
            if (id != -1) {
                val index = resources.getResourceEntryName(id).split("_")[2].toInt() - 1
                selectedIndices.add(index)
            }
        }
        return selectedIndices
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FEEDBACK_KEY = "feedback"
    }

}
