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


// The Questions page AKA MainActivity
class MainActivity : AppCompatActivity() {

    // UI Elements
    private var scoreView: TextView? = null
    private var questionView: TextView? = null
    private var numberQuestionView : TextView? = null
    private var litterImage: ImageView? = null
    private var compostBtn: ImageButton? = null
    private var recycleBtn: ImageButton? = null
    private var trashBtn: ImageButton? = null
    private var progressBar: ProgressBar? = null

    // Data Element
    private var mQuestionLibrary: QuestionLibrary? = QuestionLibrary()
    private var numberQuestion: Int = 0
    private var score: Int = 0
    private var image: Int = 0
    private var prog: Int = 5
    private var type: String = ""
    private lateinit var shuffleList: MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Other Text
        scoreView = findViewById<View>(R.id.score) as TextView
        questionView = findViewById<View>(R.id.question) as TextView
        numberQuestionView = findViewById<View>(R.id.questionNum) as TextView
        // Main Image
        litterImage = findViewById<View>(R.id.litterImage) as ImageView
        // Buttons
        compostBtn = findViewById<View>(R.id.compostBtn) as ImageButton
        recycleBtn = findViewById<View>(R.id.recycleBtn) as ImageButton
        trashBtn = findViewById<View>(R.id.trashBtn) as ImageButton
        // Progress Bar
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        // Import font (Sofia pro)
        val sofiaProBold = Typeface.createFromAsset(assets, "Fonts/sofia_pro_bold.ttf")
        val sofiaSemiBold = Typeface.createFromAsset(assets, "Fonts/sofia_pro_semibold.ttf")

        // Setting the fonts
        scoreView?.setTypeface(sofiaSemiBold)
        questionView?.setTypeface(sofiaProBold)
        numberQuestionView?.setTypeface(sofiaSemiBold)

        shuffleList = (0..19).toMutableList()
        shuffleList.shuffle()

        updateQuestion()

        // Button Handlers
        compostBtn?.setOnClickListener {
            if (type.equals("compost")) { // correct
                score += 1
               Log.d(TAG,"correct answer")
            } else { // wrong
                displayToast(type)
                Log.d(TAG, "incorrect answer")
            }
            updateQuestion()
        }

        recycleBtn?.setOnClickListener {
            if (type.equals("recycle")) { // correct
                score += 1
                Log.d(TAG,"correct answer")
            } else { // wrong
                displayToast(type)
                Log.d(TAG, "incorrect answer")
            }
            updateQuestion()
        }
        trashBtn?.setOnClickListener {
            if (type.equals("trash")) {
                score += 1
                Log.d(TAG,"correct answer")
            } else {
                displayToast(type)
                Log.d(TAG, "incorrect answer")

            }
            updateQuestion()
        }

    }

    fun updateQuestion() {
        image = mQuestionLibrary!!.getImage(shuffleList.get(numberQuestion))
        type = mQuestionLibrary!!.getType(shuffleList.get(numberQuestion))
        litterImage?.setImageResource(image)
        numberQuestion++

        // Go to end screen
        if (numberQuestion == 20) {
            val endIntent = Intent(this@MainActivity, EndScreenActivity::class.java)
            startActivity(endIntent)
        }

        progressBar?.setProgress(prog)
        prog+=5

    }
    // Displays toast messages
    fun displayToast(type : String) {
        // Compost
        if (type == "compost") {
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.view = layoutInflater.inflate(
                R.layout.toast_compost,
                findViewById(R.id.toast_compost)
            )
            toast.show()
        }
        // Recycle
        else if (type == "recycle") {
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.view = layoutInflater.inflate(
                R.layout.toast_recycle,
                findViewById(R.id.toast_recy)
            )
            toast.show()
        }
        // Trash
        else {
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.view = layoutInflater.inflate(
                R.layout.toast_trash,
                findViewById(R.id.toast_tra)
            )
            toast.show()
        }

    }

    companion object {
        val TAG = "MAIN_ACTIVITY"
    }


}
