package com.jetbrains.kmpapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileTeam(
    @SerialName("mobile_team")
    val mobileTeamMembers: List<MobileTeamMember>,
)

@Serializable
data class MobileTeamMember(
    @SerialName("_id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("designation")
    val designation: String,
)