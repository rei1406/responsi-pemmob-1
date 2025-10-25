package com.example.responsi1mobileh1d023077.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ClubResponse(
    @SerializedName("coach")
    val coach: Coach,
    @SerializedName("squad")
    val squad: List<SquadMember>
)

data class Coach(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
)

@Parcelize
data class SquadMember(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("position")
    val position: String,
) : Parcelable
