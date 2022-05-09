package com.abdykadyr.mathgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdykadyr.mathgame.R
import com.abdykadyr.mathgame.databinding.FragmentGameBinding
import com.abdykadyr.mathgame.domain.entity.GameResult
import com.abdykadyr.mathgame.domain.entity.GameSettings
import com.abdykadyr.mathgame.domain.entity.Level
import java.lang.RuntimeException

class GameFragment: Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener { launchGameFinishedFragment() }
        binding.tvOption2.setOnClickListener {  }
        binding.tvOption3.setOnClickListener {  }
        binding.tvOption4.setOnClickListener {  }
        binding.tvOption5.setOnClickListener {  }
        binding.tvOption6.setOnClickListener {  }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun createGameResult() : GameResult {
        return GameResult(
            true,
            10,
            3,
            GameSettings(20,
            10,
            50,
            100)
        )
    }

    private fun launchGameFinishedFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFinishedFragment.newInstance(createGameResult()))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level) : GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,level)
                }
            }
        }
    }
}