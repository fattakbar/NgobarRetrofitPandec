package com.zendev.ngobarretrofit.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zendev.ngobarretrofit.api.ApiClient
import com.zendev.ngobarretrofit.model.Team
import com.zendev.ngobarretrofit.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val listTeams = MutableLiveData<ArrayList<Team>>()
    private var listItems = ArrayList<Team>()

    fun setTeams() {
        val apiClient = ApiClient.create()

        apiClient.getTeam().enqueue(object : Callback<TeamResponse?> {
            override fun onFailure(call: Call<TeamResponse?>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<TeamResponse?>, response: Response<TeamResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.teams?.let {
                        listItems = response.body()!!.teams as ArrayList<Team>
                        listTeams.postValue(listItems)
                        Log.d("onResponse", listItems.toString())
                    }
                }
            }
        })
    }

    fun getTeams(): LiveData<ArrayList<Team>> {
        return listTeams
    }
}