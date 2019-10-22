package com.example.antilitter

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.graphics.Typeface
import android.widget.TextView
import android.widget.ProgressBar
import android.app.Activity
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

        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/SofiaProBold.ttf")
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
    }
}