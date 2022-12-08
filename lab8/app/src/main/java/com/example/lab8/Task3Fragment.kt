package com.example.lab8

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask3Binding
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Task3Fragment : Fragment() {
    private lateinit var binding: FragmentTask3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask3Binding.inflate(inflater)

        val view: ParticleView = ParticleView(requireContext())
        view.layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        binding.root.addView(view)

        return binding.root
    }

    private inner class ParticleView(context: Context) : View(context) {
        private var touched: Boolean = false
        private var touchedX: Float = 0f
        private var touchedY: Float = 0f
        private val particles: ArrayList<Particle> = arrayListOf<Particle>()
        private val tolerance: Float = 5f
        private val rand: Random = Random.Default

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            if (event == null) return false
            when(event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    touchedX = event.x
                    touchedY = event.y
                    touched = true
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    if (abs(event.x - touchedX) > tolerance) touchedX = event.x
                    if (abs(event.y - touchedY) > tolerance) touchedY = event.y
                    invalidate()
                }
                MotionEvent.ACTION_UP -> touched = false
            }
            return true
        }

        @SuppressLint("DrawAllocation")
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            if (particles.isEmpty() and !touched) return
            if (touched) for (i in 1..rand.nextInt(3)) {
                val vel: Float = rand.nextInt(100) / 10f + 5f
                val angle: Float = rand.nextInt(360).toFloat()
                particles.add(
                    Particle(
                        rand.nextInt(100) / 10f, touchedX, touchedY,
                        vel * cos(angle),
                        vel * sin(angle)))
            }

            for (particle: Particle in particles)
                with(particle) {
                    velX += accX
                    velY += accY
                    x += velX
                    y += velY
                }

            for (i: Int in particles.indices.reversed())
                if ((particles[i].x > width)
                    or (particles[i].x < 0)
                    or (particles[i].y > height)
                    or (particles[i].y < 0)) particles.removeAt(i)

            for (particle: Particle in particles) {
                with(particle) { canvas.drawCircle(x, y, radius, paint) }
            }

            if (particles.isNotEmpty()) invalidate()
        }
    }
}