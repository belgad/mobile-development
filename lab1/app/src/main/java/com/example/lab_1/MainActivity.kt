package com.example.lab_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var step = 0
        var num: kotlin.Double = 0.0
        var ans: kotlin.Double = 1.0
        var now: kotlin.Double = 1.0

        findViewById<Button>(R.id.button).setOnClickListener {
            if (step == 0) {
                num = ("0" + findViewById<EditText>(R.id.edittextnumber).text.toString()).toDouble()
                findViewById<TextView>(R.id.button).text = "Next step"
                findViewById<TextView>(R.id.answer).text = "1.0"
                ++step
            } else {
                now *= num / step
                ans += now
                findViewById<TextView>(R.id.answer).text = ans.toString()
                ++step
            }
        }
        findViewById<Button>(R.id.button_clear).setOnClickListener {
            step = 0
            now = 1.0
            ans = 1.0
            findViewById<EditText>(R.id.edittextnumber).text.clear()
            findViewById<TextView>(R.id.button).text = "Start"
            findViewById<TextView>(R.id.answer).text = "Enter number and press start"
        }
    }
}