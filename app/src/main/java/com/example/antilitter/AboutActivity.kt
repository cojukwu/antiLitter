package com.example.antilitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.Typeface
import android.widget.TextView

// About screen
class AboutActivity : AppCompatActivity() {
    private var aboutText : TextView? = null
    private var introOne : TextView? = null
    private var introTwo : TextView? = null
    private var creator : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_screen)

        aboutText = findViewById<View>(R.id.about) as TextView
        introOne = findViewById<View>(R.id.introOne) as TextView
        introTwo = findViewById<View>(R.id.introTwo) as TextView
        creator = findViewById<View>(R.id.creators) as TextView

    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

}