package com.example.antilitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.graphics.Typeface
import android.widget.TextView
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.content.SharedPreferences
import android.content.Context
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// End of the game Activity
class EndScreenActivity : AppCompatActivity() {
    private var title: TextView? = null
    private lateinit var scoreView : TextView
    private lateinit var username : TextView
    private lateinit var highScoreView : TextView
    private var score : Int = 0
    private var highScore : Int = 0
    private var playAgainBtn: ImageButton? = null
    private var quitBtn: ImageButton? = null
    private var resetBtn: ImageButton? = null
    private var yourTime : TextView? = null
    private var time: Long = 0
    private var hours: Int = 0
    private var minutes: Int = 0
    private var seconds: Int = 0
    private var flare : ImageView? = null
    private var thumbDown: ImageView? = null
    private var newUserName: String = ""
    private lateinit var prefs: SharedPreferences

    internal lateinit var databaseGames: DatabaseReference

    // Animation
    private var animation: Animation? = null

    // User information
    internal lateinit var UserId: String
    internal lateinit var UserMail: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_screen)

        thumbDown = findViewById(R.id.down) as ImageView
        flare = findViewById<View>(R.id.flare) as ImageView
        title = findViewById<View>(R.id.congratsTitle) as TextView
        scoreView = findViewById<View>(R.id.myScore) as TextView
        username = findViewById(R.id.usernameEnd) as TextView
        playAgainBtn = findViewById<View>(R.id.playAgainBtn) as ImageButton
        quitBtn = findViewById<View>(R.id.quitBtn) as ImageButton
        yourTime = findViewById<View>(R.id.yourTime) as TextView
        prefs = getPreferences(Context.MODE_PRIVATE)

        databaseGames = FirebaseDatabase.getInstance().getReference("games")

        // Animation
        animation = AnimationUtils.loadAnimation(this@EndScreenActivity , R.anim.animation)
        flare?.animate()?.alpha(1.toFloat())?.setDuration(1000)?.start()
        flare?.startAnimation(animation)

        thumbDown?.animate()?.alpha(1.toFloat())?.setDuration(1000)?.start()
        thumbDown?.startAnimation(animation)


        playAgainBtn?.setOnClickListener {
            val playAgainIntent = Intent(this@EndScreenActivity, MainActivity::class.java)
            playAgainIntent.putExtra(MAIL_KEY, UserMail)
            playAgainIntent.putExtra(ID_KEY, UserId)
            startActivity(playAgainIntent)

        }

        quitBtn?.setOnClickListener {
            val quitIntent = Intent(this@EndScreenActivity, HomeActivity::class.java)
            startActivity(quitIntent)
        }


        resetBtn?.setOnClickListener {
            val editor = prefs.edit()
            editor.putInt(HIGH_SCORE_KEY, 0)
            editor.apply()

            highScoreView.text = "High Score: 0"
            scoreView.text = "0/20"
        }

        // Get user information
        val intent = intent
        UserId = intent.getStringExtra(MainActivity.USER_ID)
        UserMail = intent.getStringExtra(MainActivity.USER_MAIL)
        newUserName = UserMail.substring(0, UserMail.indexOf('@'))
        scoreView.text = intent.getIntExtra(MainActivity.FINAL_SCORE, 0).toString() + "/20"

        // Displays time
        time = intent.getLongExtra(MainActivity.TIMER, 0)
        minutes = ((time - hours * 3600000) / 60000).toInt()
        seconds = ((time - hours * 3600000 - minutes * 60000) / 1000).toInt()
        yourTime?.setText("Your Time : 0${minutes}:${seconds}")

        // display username
        username.setText(newUserName)

        highScore = prefs.getInt(HIGH_SCORE_KEY, 0)
        score = intent.getIntExtra(MainActivity.FINAL_SCORE, 0)

        // Add to firebase database
        val id = databaseGames.push().key
        val game = Game(UserMail, score, time)
        databaseGames.child(id.toString()).setValue(game)

        // Change text based on score
        if (score >= 15) {
            title?.setText("Amazing!")
            thumbDown!!.visibility = View.GONE

        }

        else if (score >= 10 && score < 15) {
            title?.setText("Good Job")
            thumbDown!!.visibility = View.GONE

        }

        else if (score < 10) {
            title?.setText("Needs Some Work")
            flare!!.visibility = View.GONE
            thumbDown!!.visibility = View.VISIBLE

        }

        if (score > highScore) {

            // Get and edit high score
            val editor = prefs.edit()
            editor.putInt(HIGH_SCORE_KEY, score)
            editor.apply()

        }

    }
    // Adds transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

    companion object {
            val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"
            val MAIL_KEY = "com.example.antilitter.myhomelibrary.UMail"
            val ID_KEY = "com.example.antilitter.myhomelibrary.UID"
    }
}