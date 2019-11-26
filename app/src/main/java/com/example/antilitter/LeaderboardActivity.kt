package com.example.antilitter

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingwithmitch.kotlinrecyclerviewexample.TopSpacingItemDec
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.leaderboard_screen3.*


class LeaderboardActivity : AppCompatActivity() {
    val list = ArrayList<Game>()
    private var user1: TextView? = null
    private var user2: TextView? = null
    private var user3: TextView? = null

    private var user1time: TextView? = null
    private var user2time: TextView? = null
    private var user3time: TextView? = null

    private var user1score: TextView? = null
    private var user2score: TextView? = null
    private var user3score: TextView? = null
    private var hours: Int = 0
    private var minutes: Int = 0
    private var seconds: Int = 0
    private lateinit var sorted : List<Game>

    //private lateinit var listView: ListView

    internal lateinit var games: MutableList<Game>
    internal lateinit var databaseGames: DatabaseReference

    private lateinit var blogAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard_screen3)

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
                sorted = games.sortedWith(gameComparator)
                sorted.forEach{ Log.d("LEADERBOARD","score: ${it.score} time: ${it.time} email: ${it.userEmail}")}

                // Top 10 scores
                for(i in 0..9) {
                    list.add(i, sorted.get(i))
                }
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

                fun addDataSet() {
                    val data = list
                    blogAdapter.submitList(data)
                }

                initRecyclerView()
                addDataSet()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("LEADERBOARD", "The read failed.")
            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(this@LeaderboardActivity)
        //val topSpacingItemDec = TopSpacingItemDec(30)
        //recycler_view.addItemDecoration(topSpacingItemDec)
        blogAdapter = RecyclerAdapter()
        recycler_view.adapter = blogAdapter

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
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