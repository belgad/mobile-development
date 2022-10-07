package com.example.lab_3

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var task4Counter: Int = 0
    private val calendar: Calendar = Calendar.getInstance()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TASK 2
        findViewById<Button>(R.id.task2_button).setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                view.isActivated = !view.isActivated
            }
            true
        }

        // TASK 3
        findViewById<Button>(R.id.task3_button).setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                findViewById<TextView>(R.id.task3_text).text = "Нажата"
            } else if (event.action == MotionEvent.ACTION_UP) {
                findViewById<TextView>(R.id.task3_text).text = "Отпущена"
            }
            true
        }

        // TASK 4
        findViewById<Button>(R.id.task4_button).setOnClickListener {
            (it as Button).text = (++task4Counter).toString()
        }

        // TASK 5
        findViewById<Button>(R.id.task5_button).setOnClickListener {
            val datePickerListener: DatePickerDialog.OnDateSetListener =
                DatePickerDialog.OnDateSetListener { _, y, m, d ->
                    Log.i("Task 5", "$y-$m-$d")
                }
            DatePickerDialog(
                this, datePickerListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // TASK 6
        findViewById<Button>(R.id.task6_button).setOnClickListener {
            val timePickerListener: TimePickerDialog.OnTimeSetListener =
                TimePickerDialog.OnTimeSetListener { _, h, m ->
                    Log.i("Task 6", "$h:$m")
                }
            TimePickerDialog(
                this, timePickerListener,
                calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
            ).show()
        }

        // TASK 7
        val task7List = arrayListOf("Item 1", "Item 2", "Item 3", "Item 4")
        val spinner = findViewById<Spinner>(R.id.task7_spinner)
        ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, task7List).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                Log.i("Task 7", parent.getItemAtPosition(pos).toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        // TASK 8
        findViewById<SwitchCompat>(R.id.task8_switch).setOnCheckedChangeListener { _, state ->
            if (state) {
                findViewById<TextView>(R.id.task8_text).text = "Включен"
            } else {
                findViewById<TextView>(R.id.task8_text).text = "Выключен"
            }
        }

        // TASK 9
        findViewById<SeekBar>(R.id.task9_seekbar).setOnSeekBarChangeListener(
            object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    findViewById<TextView>(R.id.task9_text).text = progress.toString()
                }
                override fun onStartTrackingTouch(seekbar: SeekBar?) {}
                override fun onStopTrackingTouch(seekbar: SeekBar?) {}
            }
        )
    }
}
