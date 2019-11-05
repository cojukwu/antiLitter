package com.example.antilitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.graphics.Typeface
import android.widget.TextView
import android.content.Intent

// The Home Page Activity
class HomeActivity : AppCompatActivity() {

    // UI Elements
    private var title: TextView? = null
    private var playBtn: ImageButton? = null
    private var aboutBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        title = findViewById<View>(R.id.title) as TextView
        playBtn = findViewById<View>(R.id.playBtn) as ImageButton
        aboutBtn = findViewById<View>(R.id.aboutBtn) as ImageButton

        // Import font (Sofia pro)
        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/sofia_pro_bold.ttf")

        // Set title font to Sofia pro
        title?.setTypeface(sofiaPro)

        // Play Button Pressed
        playBtn?.setOnClickListener{
            val gameIntent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(gameIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

        aboutBtn?.setOnClickListener{
            val aboutIntent = Intent(this@HomeActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

    }
}