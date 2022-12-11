package com.example.lab8

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.doOnPreDraw
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
        private lateinit var sun: Bitmap
        private val colorFilter: PorterDuffColorFilter = PorterDuffColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.SRC_IN)
        private lateinit var rotateMatrix: Matrix
        private var degrees: Int = 0
        private var flag: Boolean = false

        @SuppressLint("DrawAllocation")
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val width: Float = width.toFloat()
            val height: Float = height.toFloat()

            if (!flag) {
                sun = with(ResourcesCompat.getDrawable(resources, R.drawable.sun, null)!!) {
                val size: Int = width.toInt() / 4
                    toBitmap(size, size)
                }
                flag = true
            }

            rotateMatrix = Matrix().apply{
                postRotate(degrees.toFloat(), width / 8, width / 8)
                postTranslate(width.toFloat() * 3 / 4, 0F)
            }

            paint.color = Color.CYAN
            canvas.drawRect(0F, 0F, width, height * 2 / 3, paint)

            paint.color = Color.GREEN
            canvas.drawRect(0F, height * 2 / 3, width, height, paint)

            paint.color = Color.parseColor("#FFA500")
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

            paint.colorFilter = colorFilter
            canvas.drawBitmap(sun, rotateMatrix, paint)
            paint.colorFilter = null

            degrees += 1

            invalidate()
        }
    }
}