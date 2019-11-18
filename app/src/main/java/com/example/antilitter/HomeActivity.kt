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
    private var loginBtn : ImageButton? = null
    private var registerBtn : ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        title = findViewById<View>(R.id.title) as TextView
        playBtn = findViewById<View>(R.id.playBtn) as ImageButton
        aboutBtn = findViewById<View>(R.id.aboutBtn) as ImageButton
        loginBtn = findViewById<View>(R.id.loginBtn) as ImageButton
        registerBtn = findViewById<View>(R.id.signUpBtn) as ImageButton

        // Play Button Pressed
        playBtn?.setOnClickListener{
            val gameIntent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(gameIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

        // About Button Pressed
        aboutBtn?.setOnClickListener{
            val aboutIntent = Intent(this@HomeActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

        // SignUp (register) Button Pressed
        registerBtn?.setOnClickListener {
            val regIntent = Intent(this@HomeActivity, RegistrationActivity::class.java)
            startActivity(regIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

        // Login Button Pressed
        loginBtn?.setOnClickListener {
            val loginIntent = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(loginIntent)
            overridePendingTransition(R.anim.trans, R.anim.out)
        }

    }

    // Adds transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }
}