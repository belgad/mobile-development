package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SixthTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth_task)

        findViewById<Button>(R.id.button6).setOnClickListener {
            val a = findViewById<EditText>(R.id.number1).text.toString()
            val b = findViewById<EditText>(R.id.number2).text.toString()
            Log.i("SixthTaskCalculation", (a.toDouble() + b.toDouble()).toString())
        }
    }
}