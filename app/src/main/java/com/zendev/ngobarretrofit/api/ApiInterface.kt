package com.zendev.ngobarretrofit.api

import com.zendev.ngobarretrofit.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/v1/json/1/lookup_all_teams.php?id=4328")
    fun getTeam(): Call<TeamResponse>
}