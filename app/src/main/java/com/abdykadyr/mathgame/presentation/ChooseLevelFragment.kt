package com.abdykadyr.mathgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abdykadyr.mathgame.R
import com.abdykadyr.mathgame.databinding.FragmentChooseLevelBinding
import com.abdykadyr.mathgame.domain.entity.Level
import java.lang.RuntimeException

class ChooseLevelFragment: Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            cvTestLevel.setOnClickListener { launchGameWithCurrentLevel(Level.TEST) }
            cvEasyLevel.setOnClickListener { launchGameWithCurrentLevel(Level.EASY) }
            cvNormalLevel.setOnClickListener { launchGameWithCurrentLevel(Level.NORMAL) }
            cvHardLevel.setOnClickListener { launchGameWithCurrentLevel(Level.HARD) }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private  fun launchGameWithCurrentLevel(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(ChooseLevelFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "choose_level_fragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

}