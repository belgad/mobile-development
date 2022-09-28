package com.example.lab_2

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FifthTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth_task)

        val view5 = findViewById<View>(R.id.view5)
        val animator = ObjectAnimator.ofPropertyValuesHolder(view5,
            PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 1000f),
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f))
        animator.duration = 2000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.RESTART
        animator.start()
    }
}