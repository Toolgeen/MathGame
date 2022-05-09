package com.abdykadyr.mathgame.domain.usecases

import com.abdykadyr.mathgame.domain.entity.GameSettings
import com.abdykadyr.mathgame.domain.entity.Level
import com.abdykadyr.mathgame.domain.repository.GameRepository

class GetGameSettingsUseCase (private val repository: GameRepository) {

        operator fun invoke(level: Level): GameSettings {
            return repository.getGameSettings(level)
        }

}