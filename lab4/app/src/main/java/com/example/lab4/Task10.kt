package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class Task10 : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task10_activity)

        val values: Array<String> = arrayOf("Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6")

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, values)
        val listview = findViewById<ListView>(R.id.task10_listview)
        listview.adapter = adapter

        registerForContextMenu(listview)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.task10_menu, menu)
    }

    override fun onContextItemSelected(menuItem: MenuItem): Boolean {
        val info = menuItem.menuInfo as AdapterView.AdapterContextMenuInfo
        var id = -1
        id = when (menuItem.itemId) {
            R.id.menuitem1 -> 1
            R.id.menuitem2 -> 2
            R.id.menuitem3 -> 3
            R.id.menuitem4 -> 4
            else -> return false
        }
        Log.i("Task10", "Title: ${(info.targetView as? TextView)?.text}; Row ID: ${info.id}; Context Element: $id")
        return true
    }
}