package com.example.antilitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.graphics.Typeface
import android.widget.TextView
import android.widget.ProgressBar
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.Toast

// This activity displays the scores and times of users of
// this app
class ScoresActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scores_screen)


    }

    // Transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }

}