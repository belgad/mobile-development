package com.example.lab8

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask3Binding
import kotlin.random.Random

class Task4Fragment : Fragment() {
    private lateinit var binding: FragmentTask3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask3Binding.inflate(inflater)

        val view: ParticleView = ParticleView(requireContext())
        view.layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        binding.root.addView(view)

        return binding.root
    }

    private inner class ParticleView(context: Context) : View(context) {
        private val particles: ArrayList<Particle> = arrayListOf<Particle>()
        private val rand: Random = Random.Default
        private val paints: Array<Paint> = arrayOf(
            Paint().apply { color = Color.parseColor("#ff0000") },
            Paint().apply { color = Color.parseColor("#ff8000") },
            Paint().apply { color = Color.parseColor("#ffff00") },
            Paint().apply { color = Color.parseColor("#80ff00") },
            Paint().apply { color = Color.parseColor("#00ff00") },
            Paint().apply { color = Color.parseColor("#00ff80") },
            Paint().apply { color = Color.parseColor("#00ffff") },
            Paint().apply { color = Color.parseColor("#0080ff") },
            Paint().apply { color = Color.parseColor("#0000ff") },
            Paint().apply { color = Color.parseColor("#8000ff") },
            Paint().apply { color = Color.parseColor("#ff00ff") },
            Paint().apply { color = Color.parseColor("#ff0080") }
        )

        @SuppressLint("DrawAllocation")
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            for (i in 1..rand.nextInt(3)) {
                particles.add(
                    Particle(
                        rand.nextInt(100) / 10f + 10f, width / 2f, height.toFloat(),
                        (rand.nextInt(40) / 10f + 0.5f) * (if (rand.nextBoolean()) 1f else -1f),
                        rand.nextInt(200) / -10f - 50f,
                        0f, 1f, paints.random()))
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
                    or (particles[i].y > height)) particles.removeAt(i)

            for (particle: Particle in particles) {
                with(particle) { canvas.drawCircle(x, y, radius, paint) }
            }

            invalidate()
        }
    }
}