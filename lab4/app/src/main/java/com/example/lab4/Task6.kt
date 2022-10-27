package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.Calendar

fun Boolean.toInt() = if (this) 1 else 0

class Task6 : AppCompatActivity() {
    private var tasks: ArrayList<HashMap<String, String>> = arrayListOf()
    private val date_format = SimpleDateFormat("dd.MM.yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task6_activity)

        val adapter = SimpleAdapter(this, tasks,
            R.layout.task6_fragment, arrayOf("date", "desc"),
            intArrayOf(R.id.task6_fragment_date, R.id.task6_fragment_description))
        findViewById<Button>(R.id.task6_button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialog_view = layoutInflater.inflate(R.layout.task6_dialog, null)
            builder.setView(dialog_view)
                .setPositiveButton("Add task") { _, _ ->
                    val date: String = dialog_view.findViewById<DatePicker>(R.id.task6_datepicker).run{
                        date_format.format(Calendar.getInstance().also{
                            it.set(this.year, this.month, this.dayOfMonth)}.time)}
                    val desc: String = dialog_view.findViewById<EditText>(R.id.task6_edittext).text.toString()
                    addTask(date, desc)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("Cancel") { p, _ ->
                    p.cancel()
                }
                .show()
        }
        findViewById<ListView>(R.id.task6_listview).adapter = adapter
    }
    private fun addTask(date: String, desc: String) {
        fun compare(date1: String, date2: String): Int {
            if (date1 == "") return 1
            val left = date_format.parse(date1)!!
            val right = date_format.parse(date2)!!
            return right.after(left).toInt() - left.after(right).toInt()
        }

        var found = 0
        var success = 1
        while (found < tasks.size) {
            success = compare(tasks[found]["date"]!!, date)
            if (success <= 0) break
            ++found
        }
        if (success == 0) {
            ++found
            while (found < tasks.size) {
                if (tasks[found]["date"] != "") break
                ++found
            }
        }
        tasks.add(found, hashMapOf("date" to (if (success == 0) "" else date), "desc" to desc))
    }
}