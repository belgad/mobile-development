package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener {
            startActivity(Intent(this, Task1::class.java))
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, Task2::class.java))
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, Task3::class.java))
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            startActivity(Intent(this, Task4::class.java))
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            startActivity(Intent(this, Task5::class.java))
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            startActivity(Intent(this, Task6::class.java))
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            startActivity(Intent(this, Task7::class.java))
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            startActivity(Intent(this, Task8::class.java))
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            startActivity(Intent(this, Task9::class.java))
        }
        findViewById<Button>(R.id.button10).setOnClickListener {
            startActivity(Intent(this, Task10::class.java))
        }
        findViewById<Button>(R.id.button11).setOnClickListener {
            startActivity(Intent(this, Task11::class.java))
        }
    }
}