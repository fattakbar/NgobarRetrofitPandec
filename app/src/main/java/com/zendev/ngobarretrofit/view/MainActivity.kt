package com.zendev.ngobarretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.zendev.ngobarretrofit.R
import com.zendev.ngobarretrofit.model.Team
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        adapter = MainAdapter(teams) {
            Toast.makeText(this, it.strTeam, Toast.LENGTH_SHORT).show()
        }

        rv_team.adapter = adapter
        rv_team.layoutManager = GridLayoutManager(this, 2)

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            progressbar.visibility = View.VISIBLE

            mainViewModel.setTeams()
        }

        mainViewModel.setTeams()

        // ViewModel Observe
        mainViewModel.getTeams().observe(this, Observer { data ->
            swipe_refresh.isRefreshing = false
            progressbar.visibility = View.INVISIBLE
            teams.clear()
            teams.addAll(data)
            adapter.notifyDataSetChanged()
        })
    }
}
