package com.example.lab4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Calendar

class Task4 : AppCompatActivity() {
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task4_activity)

        findViewById<Button>(R.id.task4_button).setOnClickListener {
            DatePickerDialog(
                this, { _, y, m, d -> findViewById<TextView>(R.id.task4_textview).text = "$y-$m-$d" },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}