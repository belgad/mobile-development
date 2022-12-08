package com.example.lab8

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask1Binding

class Task1Fragment : Fragment() {
    private lateinit var binding: FragmentTask1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask1Binding.inflate(inflater)

        val customView: CanvasView = CanvasView()
        customView.layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT).also {
            it.setMargins((resources.displayMetrics.density * 0).toInt()) }
        binding.root.addView(customView)

        return binding.root
    }

    private inner class CanvasView : View(requireContext()) {
        private val paint: Paint = Paint().apply { isAntiAlias = true }
        private val path: Path = Path()

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val width: Float = width.toFloat()
            val height: Float = height.toFloat()

            paint.color = Color.CYAN
            canvas.drawRect(0f, 0f, width, height * 2 / 3, paint)

            paint.color = Color.GREEN
            canvas.drawRect(0f, height * 2 / 3, width, height, paint)

            paint.color = Color.parseColor("#ffa500")
            canvas.drawRect(width / 3, height / 2, width * 2 / 3, height * 3 / 4, paint)

            paint.color = Color.RED
            canvas.drawRect(width * 7 / 12, height / 5, width * 31 / 48, height/ 2, paint)

            paint.color = Color.parseColor("#964B00")
            with(path) {
                moveTo(width / 4, height / 2)
                lineTo(width / 2, height / 4)
                lineTo(width * 3 / 4, height / 2)
                close()
                canvas.drawPath(path, paint)
                reset()
            }
        }
    }
}