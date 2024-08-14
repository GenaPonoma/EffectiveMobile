package com.example.skillbox_hw_quiz.quiz

import android.os.Parcelable
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.R.string.answer2_1
import com.example.skillbox_hw_quiz.R.string.answer2_2
import com.example.skillbox_hw_quiz.R.string.answer2_3
import com.example.skillbox_hw_quiz.R.string.answer2_4
import com.example.skillbox_hw_quiz.R.string.answer3_1
import com.example.skillbox_hw_quiz.R.string.answer3_2
import com.example.skillbox_hw_quiz.R.string.answer3_3
import com.example.skillbox_hw_quiz.R.string.answer3_4
import com.example.skillbox_hw_quiz.R.string.feedback2_1
import com.example.skillbox_hw_quiz.R.string.feedback2_2
import com.example.skillbox_hw_quiz.R.string.feedback2_3
import com.example.skillbox_hw_quiz.R.string.feedback2_4
import com.example.skillbox_hw_quiz.R.string.feedback3_1
import com.example.skillbox_hw_quiz.R.string.feedback3_2
import com.example.skillbox_hw_quiz.R.string.feedback3_3
import com.example.skillbox_hw_quiz.R.string.feedback3_4
import com.example.skillbox_hw_quiz.R.string.question1
import com.example.skillbox_hw_quiz.R.string.question2
import com.example.skillbox_hw_quiz.R.string.question3
import kotlinx.parcelize.Parcelize


@Parcelize
object QuizStorage : Parcelable {


    fun getQuiz(): Quiz {
        return quiz
    }


    fun answer(quiz: Quiz, answers: List<Int>): String = quiz
        .questions
        .zip(answers) { question, index -> question.feedback[index] }
        .joinToString(separator = " ")


    private val quiz = object : Quiz {


        override val questions: List<Question> = listOf(
            Question(
                question = question1,


                answers = listOf(
                    R.string.answer1_1,
                    R.string.answer1_2,
                    R.string.answer1_3,
                    R.string.answer1_4,

                    ),
                feedback = listOf(
                    R.string.feedback1_1,
                    R.string.feedback1_2,
                    R.string.feedback1_3,
                    R.string.feedback1_4,
                ),
            ),
            Question(
                question = question2,
                answers = listOf(
                    answer2_1,
                    answer2_2,
                    answer2_3,
                    answer2_4,
                ),
                feedback = listOf(
                    feedback2_1,
                    feedback2_2,
                    feedback2_3,
                    feedback2_4,
                ),
            ),
            Question(
                question = question3,
                answers = listOf(
                    answer3_1,
                    answer3_2,
                    answer3_3,
                    answer3_4,
                ),
                feedback = listOf(
                    feedback3_1,
                    feedback3_2,
                    feedback3_3,
                    feedback3_4,
                ),
            ),
        )
    }

//    private fun getString(stringId: Int): String {
//        return toString().get(stringId).toString()
//    }


//    private val quizEn = object : Quiz {
//        override val questions: List<Question> = listOf(
//            Question(
//                question = "Please, Rate Android course?",
//                answers = listOf(
//                    "F",
//                    "C",
//                    "B",
//                    "A++",
//                ),
//                feedback = listOf(
//                    "You have rated us F",
//                    "You have rated us C",
//                    "You have rated us B",
//                    "You have rated us A++",
//                ),
//            ),
//            Question(
//                question = "Do you want to recommend us to your friends?",
//                answers = listOf(
//                    "Sure",
//                    "Already recommend",
//                    "I don't know",
//                    "No",
//                ),
//                feedback = listOf(
//                    "You want to recommend our course",
//                    "You already recommend our course",
//                    "You need more time for make recommendations",
//                    "You are not ready to recommendations",
//                ),
//            ),
//            Question(
//                question = "Did you use the course feedback?",
//                answers = listOf(
//                    "Yes",
//                    "Not yet, but now I will use it",
//                    "No, it hasn't happened yet",
//                    "No, and I won't",
//                ),
//                feedback = listOf(
//                    "Thanks for feedback!",
//                    "Thanks for feedback!",
//                    "Waiting for your feedback!",
//                    "We hope you will have the opportunity to give us feedback",
//                ),
//            ),
//        )
//    }

}