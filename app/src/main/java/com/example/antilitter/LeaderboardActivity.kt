package com.example.antilitter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class LeaderboardActivity : AppCompatActivity() {

    private var user1: TextView? = null
    private var user2: TextView? = null
    private var user3: TextView? = null

    private var user1time: TextView? = null
    private var user2time: TextView? = null
    private var user3time: TextView? = null

    private var user1score: TextView? = null
    private var user2score: TextView? = null
    private var user3score: TextView? = null
    private var time: Long = 0
    private var hours: Int = 0
    private var minutes: Int = 0
    private var seconds: Int = 0

    private lateinit var listView: TextView

    internal lateinit var games: MutableList<Game>
    internal lateinit var databaseGames: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard_screen)

        val listItems = arrayOfNulls<String>(10)

//        user1 = findViewById(R.id.user1)
//        user1time = findViewById(R.id.user1time)
//        user1score = findViewById(R.id.user1score)
//
//        user2 = findViewById(R.id.user2)
//        user2time = findViewById(R.id.user2time)
//        user2score = findViewById(R.id.user2score)
//
//        user3 = findViewById(R.id.user3)
//        user3time = findViewById(R.id.user3time)
//        user3score = findViewById(R.id.user3score)

        games = ArrayList()

        databaseGames = FirebaseDatabase.getInstance().getReference("games")

        databaseGames.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                games.clear()

                //iterating through all the nodes
                for (postSnapshot in dataSnapshot.children) {
                    //getting game
                    val game = postSnapshot.getValue<Game>(Game::class.java)

                    //adding author to the list
                    games.add(game!!)

                }
                val sorted = games.sortedWith(gameComparator)
                sorted.forEach{ Log.d("LEADERBOARD","score: ${it.score} time: ${it.time} email: ${it.userEmail}")}
                Log.d("LEADERBOARD", sorted.toString())

//                user1!!.setText(sorted[0].userEmail)
//                user1score!!.setText(sorted[0].score.toString())
//                user1time!!.setText(setTime(sorted[0].time.toInt()))
//
//                user2!!.setText(sorted[1].userEmail)
//                user2score!!.setText(sorted[1].score.toString())
//                user2time!!.setText(setTime(sorted[1].time.toInt()))
//
//
//                user3!!.setText(sorted[2].userEmail)
//                user3score!!.setText(sorted[2].score.toString())
//                user3time!!.setText(setTime(sorted[2].time.toInt()))

                for (i in 0 until 10) {
                    val game = sorted[i]
                    listItems[i] = game.userEmail + "   " + game.score.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("LEADERBOARD", "The read failed.")
            }
        })

        var listAdapter = ListViewAdapter(
            this,
            R.layout.leaderboard_screen,
            listItems
        )




//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        //listView.adapter = adapter
        //listView.setAdapter(adapter)
    }



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

    fun setTime(time : Int) : String{
        var retTime = ""
        minutes = ((time - hours * 3600000) / 60000)
        seconds = ((time - hours * 3600000 - minutes * 60000) / 1000)
        retTime = "0${minutes}:${seconds}"
        return retTime
    }

    val gameComparator =  object: Comparator<Game> {
        override fun compare(o1: Game, o2: Game): Int {
            if (o1.score < o2.score) {
                return 1
            } else if (o1.score > o2.score) {
                return -1
            } else if (o1.time > o2.time){
                return 1
            } else if (o1.time < o2.time){
                return -1
            }
            else {
                return 0
            }
        }
    }

}