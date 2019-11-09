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
import android.widget.Chronometer


// The Questions page AKA MainActivity
class MainActivity : AppCompatActivity() {
    // UI Elements
    private lateinit var scoreView: TextView
    private var questionView: TextView? = null
    private lateinit var numberQuestionView : TextView
    private var factTrash : TextView? = null
    private var factComp : TextView? = null
    private var factRecy : TextView? = null
    private var litterImage: ImageView? = null
    private var compostBtn: ImageButton? = null
    private var recycleBtn: ImageButton? = null
    private var trashBtn: ImageButton? = null
    private var progressBar: ProgressBar? = null
    private var timer : Chronometer? = null

    // Data Element
    private var mQuestionLibrary: QuestionLibrary? = QuestionLibrary()
    private var numberQuestion: Int = 0
    var score: Int = 0
    private var image: Int = 0
    private var prog: Int = 5
    private var type: String = ""
    private lateinit var shuffleList: MutableList<Int>

    // List of Facts
    val facts = listOf(R.string.s1, R.string.s2, R.string.s3, R.string.s4, R.string.s5,
        R.string.s6, R.string.s7, R.string.s8, R.string.s9, R.string.s11,
        R.string.s12, R.string.s14, R.string.s16, R.string.s17, R.string.s18, R.string.s19,
        R.string.s20).toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Other Text
        scoreView = findViewById<View>(R.id.score) as TextView
        scoreView.text = "Score: 0"
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
        timer = findViewById<View>(R.id.chronometer) as Chronometer

        // Import font (Sofia pro)
        val sofiaProBold = Typeface.createFromAsset(assets, "Fonts/sofia_pro_bold.ttf")
        val sofiaSemiBold = Typeface.createFromAsset(assets, "Fonts/sofia_pro_semibold.ttf")

        timer?.start()

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
                scoreView.text = "Score: ${score}"
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
                scoreView.text = "Score: ${score}"
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
                scoreView.text = "Score: ${score}"
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
        numberQuestionView.text = numberQuestion.toString() + "/20"
        Log.d(TAG, numberQuestion.toString())
        // Go to end screen
        if (numberQuestion == 20) {
            val endIntent = Intent(this@MainActivity, EndScreenActivity::class.java)
            endIntent.putExtra(TIMER, timer?.base)
            endIntent.putExtra(FINAL_SCORE, score)
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
            toast.view = layoutInflater.inflate(R.layout.toast_compost, findViewById(R.id.toast_comp))

            // Sets new Random fact
            facts.shuffle()
            factComp = toast.view.findViewById<View>(R.id.fact_comp) as TextView
            factComp?.setText(facts.get(0))
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
            // Sets new random fact
            facts.shuffle()
            factRecy = toast.view.findViewById<View>(R.id.fact_rec) as TextView
            factRecy?.setText(facts.get(0))
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
            // Sets new random fact
            facts.shuffle()
            factTrash = toast.view.findViewById<View>(R.id.fact_trash) as TextView
            factTrash?.setText(facts.get(0))
            toast.show()
        }

    }

    // Adds transitions
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans2, R.anim.out2)
    }
    
    companion object {
        val TAG = "MAIN_ACTIVITY"
        val TIMER = "TIMER"
        val FINAL_SCORE = "FINAL_SCORE"
    }


}
