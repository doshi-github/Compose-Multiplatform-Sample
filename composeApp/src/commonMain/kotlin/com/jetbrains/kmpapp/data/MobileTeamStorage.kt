package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface MobileTeamStorage {
    suspend fun saveObjects(mobileTeam: MobileTeam)

    fun getObjects(): Flow<MobileTeam>
}

class InMemoryMobileTeamStorage : MobileTeamStorage {
    private val storedObjects = MutableStateFlow(MobileTeam(emptyList()))
    
    override suspend fun saveObjects(mobileTeam: MobileTeam) {
        storedObjects.value = mobileTeam
    }

    override fun getObjects(): Flow<MobileTeam> = storedObjects
}