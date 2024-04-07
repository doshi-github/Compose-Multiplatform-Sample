package com.jetbrains.kmpapp.screens.mobile_team

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.jetbrains.kmpapp.data.MobileTeamMember
import com.jetbrains.kmpapp.screens.EmptyScreenContent

data object MobileTeamScreen : Screen {
    @Composable
    override fun Content() {
        val mobileTeamModel: MobileTeamScreenModel = getScreenModel()
        val mobileTeam by mobileTeamModel.mobileTeam.collectAsState()

        AnimatedContent(mobileTeam.mobileTeamMembers.isNotEmpty()) { objectsAvailable ->
            if (objectsAvailable) {
                MobileTeamGrid(objects = mobileTeam.mobileTeamMembers)
            }
            else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MobileTeamGrid(
    objects: List<MobileTeamMember>,
    modifier: Modifier = Modifier) {
    LazyRow() {  }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)) {
        items(objects, key = { it.id }) { obj ->
            MobileTeamMemberItem(obj = obj)
        }
    }
}

@Composable
private fun MobileTeamMemberItem(
    obj: MobileTeamMember,
    modifier: Modifier = Modifier) {
    Column(modifier.padding(8.dp)) {
        Spacer(Modifier.height(2.dp))
        Text(obj.name, style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold))
        Text(obj.designation, style = MaterialTheme.typography.body2)
        Text(obj.id, style = MaterialTheme.typography.caption)
    }
}