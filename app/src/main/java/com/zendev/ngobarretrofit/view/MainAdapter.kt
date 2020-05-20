package com.zendev.ngobarretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zendev.ngobarretrofit.R
import com.zendev.ngobarretrofit.model.Team

class MainAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bindItem(teams[position], listener)

    override fun getItemCount(): Int = teams.size
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamImage: ImageView = view.findViewById(R.id.team_poster)
    private val teamName: TextView = view.findViewById(R.id.team_name)

    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Picasso.get().load(teams.strTeamBadge).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(teamImage)

        teamName.text = teams.strTeam

        itemView.setOnClickListener {
            listener(teams)
        }
    }
}
