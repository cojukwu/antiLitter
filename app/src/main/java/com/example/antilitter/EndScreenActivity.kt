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

// End of the game Activity
class EndScreenActivity : AppCompatActivity() {
    private var title: TextView? = null
    private var score : TextView? = null
    private var playAgainBtn: ImageButton? = null
    private var quitBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_screen)

        title = findViewById<View>(R.id.congratsTitle) as TextView
        score = findViewById<View>(R.id.myScore) as TextView
        playAgainBtn = findViewById<View>(R.id.playAgainBtn) as ImageButton
        quitBtn = findViewById<View>(R.id.quitBtn) as ImageButton

        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/SofiaProBold.ttf")
        title?.setTypeface(sofiaPro)
        score?.setTypeface(sofiaPro)

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