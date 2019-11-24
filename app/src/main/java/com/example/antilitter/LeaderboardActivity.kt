package com.example.antilitter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
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

    internal lateinit var games: MutableList<Game>
    internal lateinit var databaseGames: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard_screen)

        user1 = findViewById(R.id.user1)
        user1time = findViewById(R.id.user1time)
        user1score = findViewById(R.id.user1score)

        user2 = findViewById(R.id.user2)
        user2time = findViewById(R.id.user2time)
        user2score = findViewById(R.id.user2score)

        user3 = findViewById(R.id.user3)
        user3time = findViewById(R.id.user3time)
        user3score = findViewById(R.id.user3score)

        games = ArrayList()

        databaseGames = FirebaseDatabase.getInstance().getReference("games")

        // For some reason the program is never triggering the listener (doesn't go inside the method below)
        // Maybe because we're initializing the database twice (here and in EndScreenActivity)?
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
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("LEADERBOARD", "The read failed.")
            }
        })

        val sorted = games.sortedWith(gameComparator)
        sorted.forEach{ Log.d("LEADERBOARD","score: ${it.score} time: ${it.time} email: ${it.userEmail}")}

        Log.d("LEADERBOARD", "Hello World")

    }



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }


    val gameComparator =  object: Comparator<Game> {
        override fun compare(o1: Game, o2: Game): Int {
            if (o1.score > o2.score) {
                return 1;
            } else if (o1.score < o2.score) {
                return -1;
            } else {
                return o1.time.compareTo(o2.time);
            }
        }
    }

}