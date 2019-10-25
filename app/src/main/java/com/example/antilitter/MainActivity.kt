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

// The Questions page AKA MainActivity
class MainActivity : AppCompatActivity() {

    // UI Elements
    private var score: TextView? = null
    private var question: TextView? = null
    private var numberQuestion : TextView? = null
    private var litterImage: ImageView? = null
    private var compostBtn: ImageButton? = null
    private var recycleBtn: ImageButton? = null
    private var trashBtn: ImageButton? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Other Text
        score = findViewById<View>(R.id.score) as TextView
        question = findViewById<View>(R.id.question) as TextView
        numberQuestion = findViewById<View>(R.id.questionNum) as TextView
        // Main Image
        litterImage = findViewById<View>(R.id.litterImage) as ImageView
        // Buttons
        compostBtn = findViewById<View>(R.id.compostBtn) as ImageButton
        recycleBtn = findViewById<View>(R.id.recycleBtn) as ImageButton
        trashBtn = findViewById<View>(R.id.trashBtn) as ImageButton
        // Progress Bar
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        // Import font (Sofia pro)
        val sofiaPro = Typeface.createFromAsset(assets, "Fonts/SofiaProBold.ttf")

        // Setting the fonts
        score?.setTypeface(sofiaPro)
        question?.setTypeface(sofiaPro)
        numberQuestion?.setTypeface(sofiaPro)
        // hi
    }
}
