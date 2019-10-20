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

        // Import font (Sofia pro)
        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/Sofia Pro SemiBold.ttf")
        aboutText?.setTypeface(sofiaPro)
        introOne?.setTypeface(sofiaPro)
        introTwo?.setTypeface(sofiaPro)
        creator?.setTypeface(sofiaPro)

    }

}