package com.jetbrains.kmpapp.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MobileTeamRepository(
    private val mobileTeamApi: MobileTeamApi,
    private val mobileTeamStorage: MobileTeamStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        mobileTeamStorage.saveObjects(mobileTeamApi.getData())
    }

    fun getObjects(): Flow<MobileTeam> = mobileTeamStorage.getObjects()
}