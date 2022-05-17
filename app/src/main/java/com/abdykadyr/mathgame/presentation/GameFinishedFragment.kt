package com.abdykadyr.mathgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.abdykadyr.mathgame.R
import com.abdykadyr.mathgame.databinding.FragmentGameFinishedBinding

class GameFinishedFragment: Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private val gameResult by lazy {
        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindViews() {
        with(binding) {
            ivSmile.setImageResource(getSmileResId())
            tvRightAnswersTitle.text = String.format(
                getString(R.string.right_answers_title),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvTotalAnswersTitle.text = String.format(
                getString(R.string.total_answers_title),
                gameResult.countOfRightAnswers
            )
            tvPercentTitle.text = String.format(
                getString(R.string.percent_title),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvLevelTitle.text = String.format(
                getString(R.string.level_title),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_baseline_win_100
        } else {
            R.drawable.ic_baseline_loose_100
        }
    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }
}