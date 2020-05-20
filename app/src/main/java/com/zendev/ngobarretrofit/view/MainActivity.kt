package com.zendev.ngobarretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.zendev.ngobarretrofit.R
import com.zendev.ngobarretrofit.api.ApiClient
import com.zendev.ngobarretrofit.model.Team
import com.zendev.ngobarretrofit.model.TeamResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(teams) {
            Toast.makeText(this, it.strTeam, Toast.LENGTH_SHORT).show()
        }

        rv_team.adapter = adapter
        rv_team.layoutManager = GridLayoutManager(this, 2)

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            progressbar.visibility = View.VISIBLE
            fetchApi()
        }

        fetchApi()
    }

    private fun fetchApi() {
        val apiClient = ApiClient.create()

        apiClient.getTeam().enqueue(object : Callback<TeamResponse?> {

            override fun onFailure(call: Call<TeamResponse?>, t: Throwable) {
                progressbar.visibility = View.INVISIBLE
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<TeamResponse?>, response: Response<TeamResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.teams?.let {
                        progressbar.visibility = View.INVISIBLE
                        teams.clear()
                        teams.addAll(response.body()!!.teams)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
