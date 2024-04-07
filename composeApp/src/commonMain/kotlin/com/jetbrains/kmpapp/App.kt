package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.screens.mobile_team.MobileTeamScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(MobileTeamScreen)
    }
}