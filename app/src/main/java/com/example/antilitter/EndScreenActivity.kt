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

// End of the game Activity
class EndScreenActivity : AppCompatActivity() {
    private var title: TextView? = null
    private lateinit var scoreView : TextView
    private lateinit var highScoreView : TextView
    private var score : Int = 0
    private var highScore : Int = 0
    private var playAgainBtn: ImageButton? = null
    private var quitBtn: ImageButton? = null
    private var resetBtn: ImageButton? = null
    private var flare : ImageView? = null
    private var thumbsUp1 : ImageView? = null
    private var thumbsUp2 : ImageView? = null
    private var yourTime : TextView? = null
    private var time: String = ""
    private lateinit var prefs: SharedPreferences

    // Animation
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_screen)

        title = findViewById<View>(R.id.congratsTitle) as TextView
        scoreView = findViewById<View>(R.id.myScore) as TextView
        playAgainBtn = findViewById<View>(R.id.playAgainBtn) as ImageButton
        quitBtn = findViewById<View>(R.id.quitBtn) as ImageButton
        resetBtn = findViewById<View>(R.id.resetBtn) as ImageButton
        flare = findViewById<View>(R.id.flare) as ImageView
        thumbsUp1 = findViewById<View>(R.id.flare_tUp1) as ImageView
        thumbsUp2 = findViewById<View>(R.id.flare_tUp2) as ImageView
        yourTime = findViewById<View>(R.id.yourTime) as TextView
        prefs = getPreferences(Context.MODE_PRIVATE)

        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/sofia_pro_bold.ttf")
        title?.setTypeface(sofiaPro)
        scoreView?.setTypeface(sofiaPro)

        // Animation
        animation = AnimationUtils.loadAnimation(this@EndScreenActivity , R.anim.animation)
        flare?.animate()?.alpha(1.toFloat())?.setDuration(1000)?.start()
        flare?.startAnimation(animation)

        playAgainBtn?.setOnClickListener {
            val endIntent = Intent(this@EndScreenActivity, MainActivity::class.java)
            startActivity(endIntent)
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

        scoreView.text = intent.getIntExtra(MainActivity.FINAL_SCORE, 0).toString() + "/20"

        // Need to fix the time
        time = intent.getLongExtra(MainActivity.TIMER, 0).toString()
        yourTime?.setText("Your Time : 00:00")

        highScoreView = findViewById(R.id.yourHighScore)
        highScore = prefs.getInt(HIGH_SCORE_KEY, 0)
        score = intent.getIntExtra(MainActivity.FINAL_SCORE, 0)

        
        if (score >= 15) {
            title?.setText("Amazing!")
        }

        else if (score >= 10 && score < 15) {
            title?.setText("Good Job")
        }

        else if (score < 10) {
            title?.setText("Needs Some Work")
        }

        if (score > highScore) {

            // Get and edit high score
            val editor = prefs.edit()
            editor.putInt(HIGH_SCORE_KEY, score)
            editor.apply()

            highScoreView.text = "High Score:" + score.toString()
        }

        else {
            highScoreView.text = "High Score:" + highScore.toString()
        }



    }
    // Adds transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

    companion object {
            val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"
    }
}