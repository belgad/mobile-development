package com.example.lab4

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Calendar

class Task5 : AppCompatActivity() {
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task5_activity)

        findViewById<Button>(R.id.task5_button).setOnClickListener {
            TimePickerDialog(
                this, { _, h, m -> findViewById<TextView>(R.id.task5_textview).text = "$h:$m"},
                calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
            ).show()
        }
    }
}