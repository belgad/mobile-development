package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.first).setOnClickListener {
            startActivity(Intent(this, FirstTask::class.java))
        }
        findViewById<Button>(R.id.second).setOnClickListener {
            startActivity(Intent(this, SecondTask::class.java))
        }
        findViewById<Button>(R.id.third).setOnClickListener {
            startActivity(Intent(this, ThirdTask::class.java))
        }
        findViewById<Button>(R.id.fourth).setOnClickListener {
            startActivity(Intent(this, FourthTask::class.java))
        }
        findViewById<Button>(R.id.fifth).setOnClickListener {
            startActivity(Intent(this, FifthTask::class.java))
        }
        findViewById<Button>(R.id.sixth).setOnClickListener {
            startActivity(Intent(this, SixthTask::class.java))
        }
    }
}