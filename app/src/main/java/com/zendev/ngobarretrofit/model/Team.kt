package com.zendev.ngobarretrofit.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    val strTeamBadge: String? = null,

    @SerializedName("strTeam")
    val strTeam: String? = null
)