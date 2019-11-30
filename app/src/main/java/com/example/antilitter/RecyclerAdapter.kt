package com.example.antilitter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.leaderboard_screen2.view.*
import kotlin.collections.ArrayList

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val ranks = listOf(R.drawable.one, R.drawable.two,  R.drawable.three, R.drawable.four,
        R.drawable.five,  R.drawable.six,  R.drawable.seven,  R.drawable.eight,
        R.drawable.nine,  R.drawable.ten)

    private var items : List<Game> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_screen2, parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.bind(items.get(position))
                holder.itemView.rank.setImageResource(ranks.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(game : List<Game>) {
        items = game
    }

    class ViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private var hours: Int = 0
        private var minutes: Int = 0
        private var seconds: Int = 0
        val leaderboard_userName = itemView.leaderboardUserName
        val leaderboard_userScore = itemView.leaderboardScore
        val leaderboard_userTime = itemView.leaderboardTime

        fun bind(game: Game){
            leaderboard_userScore.setText(game.score.toString())
            leaderboard_userTime.setText(setTime(game.time.toInt()))
            leaderboard_userName.setText(game.userEmail.substring(0, game.userEmail.indexOf('@')))

        }

        fun setTime(time : Int) : String{
            var retTime = ""
            minutes = ((time - hours * 3600000) / 60000)
            seconds = ((time - hours * 3600000 - minutes * 60000) / 1000)
            retTime = "0${minutes}:${seconds}"
            return retTime
        }




    }



}
