package com.abdykadyr.mathgame.domain.repository

import com.abdykadyr.mathgame.domain.entity.GameSettings
import com.abdykadyr.mathgame.domain.entity.Level
import com.abdykadyr.mathgame.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int,
    ): Question

    fun getGameSettings(level: Level): GameSettings

}