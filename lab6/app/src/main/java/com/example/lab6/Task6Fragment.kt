package com.example.lab6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab6.databinding.FragmentTask6Binding
import java.util.*

class Task6Fragment : Fragment() {
    private lateinit var binding: FragmentTask6Binding
    private var timer: Timer = Timer()
    private var timerStarted: Boolean = false
    private var time: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask6Binding.inflate(inflater)

        binding.task6StartStopButton.setOnClickListener {
            if (timerStarted) {
                timer.cancel()
                timer = Timer()
                binding.task6StartStopButton.text = "Start"
                timerStarted = false
            } else {
                timer.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        time += 1
                        val timeInt: Int = time.toInt()
                        val h: Int = timeInt / 3600
                        val m: Int = timeInt % 3600 / 60
                        val s: Int = timeInt % 60
                        if (timerStarted) {
                            if (timeInt % 60 == 0) binding.task6Hour.rotation += 0.5f
                            if (timeInt % 12 == 0) binding.task6Minute.rotation +=0.5f
                            binding.task6Second.rotation += 6f
                            requireActivity().runOnUiThread {
                                binding.task6Textview.text =
                                    String.format("%02d:%02d:%02d", h, m, s)
                            }
                        }
                    }
                }, 0, 1000)
                binding.task6StartStopButton.text = "Stop"
                timerStarted = true
            }
        }
        binding.task6ResetButton.setOnClickListener {
            if (timerStarted) {
                timerStarted = false
                timer.cancel()
                timer = Timer()
                binding.task6StartStopButton.text = "Start"
            }
            time = 0.0
            binding.task6Hour.rotation = 0f
            binding.task6Minute.rotation = 0f
            binding.task6Second.rotation = 0f
            requireActivity().runOnUiThread { binding.task6Textview.text = "00:00:00" }
        }

        binding.task6Hour.viewTreeObserver.addOnDrawListener {
            with (binding.task6Hour) {
                pivotY = measuredHeight.toFloat()
            }
        }
        binding.task6Minute.viewTreeObserver.addOnDrawListener {
            with (binding.task6Minute) {
                pivotY = measuredHeight.toFloat()
            }
        }
        binding.task6Second.viewTreeObserver.addOnDrawListener {
            with (binding.task6Second) {
                pivotY = measuredHeight.toFloat()
            }
        }

        return binding.root
    }
}