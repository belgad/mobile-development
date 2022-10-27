package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class Task3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task3_activity)

        findViewById<Button>(R.id.task3_button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog_view = layoutInflater.inflate(R.layout.task3_dialog, null)
            builder.setView(dialog_view)
                .setTitle("Enter text")
                .setPositiveButton("OK") { _, _ ->
                    findViewById<TextView>(R.id.task3_textview).text =
                        dialog_view.findViewById<EditText>(R.id.task3_edittext).text.toString()
                }
                .show()
        }
    }
}