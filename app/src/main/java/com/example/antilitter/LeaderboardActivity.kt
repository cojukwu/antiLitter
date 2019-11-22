package com.example.antilitter

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



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

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

}