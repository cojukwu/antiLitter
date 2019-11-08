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

// End of the game Activity
class EndScreenActivity : AppCompatActivity() {
    private var title: TextView? = null
    private var score : TextView? = null
    private var playAgainBtn: ImageButton? = null
    private var quitBtn: ImageButton? = null
    private var flare : ImageView? = null
    private var yourTime : TextView? = null
    private var time: String = ""

    // Animation
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_screen)

        title = findViewById<View>(R.id.congratsTitle) as TextView
        score = findViewById<View>(R.id.myScore) as TextView
        playAgainBtn = findViewById<View>(R.id.playAgainBtn) as ImageButton
        quitBtn = findViewById<View>(R.id.quitBtn) as ImageButton
        flare = findViewById<View>(R.id.flare) as ImageView
        yourTime = findViewById<View>(R.id.yourTime) as TextView

        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/sofia_pro_bold.ttf")
        title?.setTypeface(sofiaPro)
        score?.setTypeface(sofiaPro)

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

        // Need to fix the time
        time = intent.getLongExtra(MainActivity.TIMER, 0).toString()
        yourTime?.setText("Your Time : ${time}")

    }
    // Adds transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }
}