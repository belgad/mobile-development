package com.example.lab4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

class Task9 : AppCompatActivity() {
    private val data1: Array<String> = arrayOf("Android", "macOS", "Aurora")
    private val data2: Array<String> = arrayOf("Element 1", "Element 2", "Element 3", "Element 4")
    private lateinit var gesture_detector: GestureDetectorCompat

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task9_activity)

        gesture_detector = GestureDetectorCompat(this, object :
            GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                event1: MotionEvent,
                event2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val distanceY = event2.y - event1.y
                if (distanceY > 100) {
                    // SWIPE UP
                    val popup_menu = PopupMenu(baseContext, findViewById(R.id.task9_barrier_top))
                    for (item in data1) {
                        popup_menu.menu.add(item)
                    }
                    popup_menu.setOnMenuItemClickListener {
                        findViewById<TextView>(R.id.task9_textview).text = it.title.toString()
                        return@setOnMenuItemClickListener true
                    }
                    popup_menu.show()
                } else if (distanceY < -100) {
                    // SWIPE DOWN
                    val popup_menu = PopupMenu(baseContext, findViewById(R.id.task9_barrier_bottom))
                    for (item in data2) {
                        popup_menu.menu.add(item)
                    }
                    popup_menu.setOnMenuItemClickListener {
                        findViewById<TextView>(R.id.task9_textview).text = it.title.toString()
                        return@setOnMenuItemClickListener true
                    }
                    popup_menu.show()
                }
                return true
            }
            override fun onDown(e: MotionEvent?): Boolean = true
        })

        findViewById<TextView>(R.id.task9_textview).setOnTouchListener { _, event ->
            gesture_detector.onTouchEvent(event)
        }
    }
}