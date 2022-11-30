package com.example.lab6

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.lab6.databinding.FragmentTask3Binding

class Task3Fragment : Fragment() {
    private lateinit var binding: FragmentTask3Binding
    private lateinit var animator: ObjectAnimator
    private var mIsReversed: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask3Binding.inflate(inflater)

        animator = ObjectAnimator.ofPropertyValuesHolder(binding.task3Textview,
            PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 1800f),
            PropertyValuesHolder.ofFloat(View.ROTATION, 180f),
            PropertyValuesHolder.ofObject("textColor", ArgbEvaluator(), 0xff000000.toInt(), 0xff00ffff.toInt())
        )
        animator.duration = 2000

        binding.root.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    if (!animator.isRunning) {
                        mIsReversed = false
                        animator.start()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (!mIsReversed) {
                        mIsReversed = true
                        animator.reverse()
                    }
                }
            }
            true
        }

        return binding.root
    }
}