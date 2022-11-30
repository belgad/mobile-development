package com.example.lab6

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.example.lab6.databinding.FragmentTask2Binding

class Task2Fragment : Fragment() {
    private lateinit var binding: FragmentTask2Binding
    private lateinit var thread: Thread

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask2Binding.inflate(inflater)

        val animator = ObjectAnimator.ofFloat(binding.task2Man, View.TRANSLATION_X, 817f)
        animator.duration = 4000
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.repeatCount = ObjectAnimator.INFINITE

        thread = object : Thread() {
            override fun run() {
                val activity = requireActivity()
                var sem: Int = 0
                val red = ColorStateList.valueOf(Color.parseColor("#ff0000"))
                val yellow = ColorStateList.valueOf(Color.parseColor("#ffff00"))
                val green = ColorStateList.valueOf(Color.parseColor("#00ff00"))

                activity.runOnUiThread {
                    animator.start()
                    animator.pause()
                }
                while (true) {
                    while (sem != 0) {}
                    activity.runOnUiThread {
                        binding.task2RedCircle.backgroundTintList = red
                        sem = 1
                    }
                    while (sem != 1) {}
                    sleep(3000)
                    activity.runOnUiThread {
                        binding.task2YellowCircle.backgroundTintList = yellow
                        sem = 2
                    }
                    while (sem != 2) {}
                    sleep(2000)
                    activity.runOnUiThread {
                        binding.task2RedCircle.backgroundTintList = null
                        binding.task2YellowCircle.backgroundTintList = null
                        binding.task2GreenCircle.backgroundTintList = green
                        animator.resume()
                        sem = 3
                    }
                    while (sem != 3) {}
                    sleep(4000)
                    activity.runOnUiThread {
                        binding.task2GreenCircle.backgroundTintList = null
                        animator.pause()
                        sem = 0
                    }
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thread.start()
    }
}