package com.example.pamo.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pamo.R
import com.example.pamo.databinding.FragmentNotificationsBinding

class QuizFragment : Fragment(), View.OnClickListener {
    private var falseButton: Button? = null
    private var trueButton: Button? = null
    private var nextButton: Button? = null
    private var questionTextView: TextView? = null
    private var correct = 0
    private var currentQuestionIndex = 0
    private val questionBank = arrayOf<Question>(
        Question(R.string.a, true),
        Question(R.string.b, true),
        Question(R.string.c, false),
        Question(R.string.d, true),
        Question(R.string.e, false),
        Question(R.string.f, false)
    )
    private val binding: FragmentNotificationsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_quiz, container, false)
        falseButton = v.findViewById<Button>(R.id.false_button)
        trueButton = v.findViewById<Button>(R.id.true_button)
        nextButton = v.findViewById<Button>(R.id.next_button)
        questionTextView = v.findViewById<TextView>(R.id.answer_text_view)
//        falseButton.setOnClickListener(this)
//        trueButton.setOnClickListener(this)
//        nextButton.setOnClickListener(this)
        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.false_button -> checkAnswer(false)
            R.id.true_button -> checkAnswer(true)
            R.id.next_button -> if (currentQuestionIndex < 7) {
                currentQuestionIndex = currentQuestionIndex + 1
                if (currentQuestionIndex == 6) {
                    questionTextView?.setText(
                        getString(
                            R.string.correct, correct
                        )
                    )
                    nextButton!!.visibility = View.INVISIBLE
                    trueButton!!.visibility = View.INVISIBLE
                    falseButton!!.visibility = View.INVISIBLE
                    if (correct > 3) questionTextView?.setText(
                        "Poprawność " + correct
                                + " "
                                + "na 6"
                    )
                } else {
                    updateQuestion()
                }
            }
        }
    }

    private fun updateQuestion() {
        questionTextView?.setText(
            questionBank[currentQuestionIndex]
                .answerResId
        )
    }

    private fun checkAnswer(userChooseCorrect: Boolean) {
        val answerIsTrue = questionBank[currentQuestionIndex]
            .isAnswerTrue
        if (userChooseCorrect == answerIsTrue) {
            correct++
        }
    }

    companion object {
        fun newInstance(): QuizFragment {
            return QuizFragment()
        }
    }
}