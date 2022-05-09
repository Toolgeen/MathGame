package com.abdykadyr.mathgame.domain.usecases

import com.abdykadyr.mathgame.domain.entity.Question
import com.abdykadyr.mathgame.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_QUESTIONS)
    }

    private companion object {
        private const val COUNT_OF_QUESTIONS = 6
    }
}
