package com.cs481.assignment_2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        Log.d(TAG,"inpGradeVal= hi")

        //"Grade Button"
        val calcGrade = findViewById<Button>(R.id.button)
        //"Text Field"
        val inpGrade = findViewById<EditText>(R.id.editTextText)
        //"Error Message"
        val errMsg = findViewById<TextView>(R.id.errMsg)
        //"Grade Message
        val gradeMsg = findViewById<TextView>(R.id.textView2)


        calcGrade.setOnClickListener {
            // *** Reset errMsg and gradeMsg ***//
            errMsg.setText("")
            gradeMsg.setText("")

            val inpGradeVal = inpGrade.text.toString()
            Log.d(TAG,"inpGradeVal= $inpGradeVal")

            val inpGradeValInt = inpGradeVal.toIntOrNull()
            Log.d(TAG,"inpGradeValInt= $inpGradeValInt")

            //*** Error Checking ***//

            //No input
            if (inpGradeVal.isEmpty()) {
                //Set Error TextView to "Please enter a score."
                errMsg.setText(R.string.err_inp_null)
            } else if (inpGradeValInt == null) {
                //Set Error Textview to "Please enter a valid number."
                errMsg.setText((R.string.err_inp_type))
            } else if (inpGradeValInt !in 0..100) {
                //Set Error TextView to "Score must be between 0 and 100."
                errMsg.setText((R.string.err_inp_rang))
            } else {
                //getGrade(inpGradeValInt)
                gradeMsg.setText(getGrade(inpGradeValInt))
            }

        }


    }

    fun getGrade(inpVal: Int): String {
        val gradeVal = when (inpVal) {
            in 0..69 -> "F"
            in 70..72 -> "C-"
            in 73..76 -> "C"
            in 77..79 -> "C+"
            in 80..82 -> "B-"
            in 83..86 -> "B"
            in 87..89 -> "B+"
            in 90..92 -> "A-"
            in 93..96 -> "A"
            in 97..100 -> "A+"
            else -> "ERROR"
        }

        Log.d(TAG, "gradeVal= $gradeVal")

        return gradeVal
    }
}
