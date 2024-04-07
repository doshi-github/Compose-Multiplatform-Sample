package com.jetbrains.kmpapp.screens.mobile_team

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.MobileTeam
import com.jetbrains.kmpapp.data.MobileTeamRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MobileTeamScreenModel(mobileTeamRepository: MobileTeamRepository) : ScreenModel {
    val mobileTeam: StateFlow<MobileTeam> =
        mobileTeamRepository.getObjects()
            .stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), MobileTeam(emptyList()))
}