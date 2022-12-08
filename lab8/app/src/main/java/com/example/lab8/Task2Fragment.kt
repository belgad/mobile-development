package com.example.lab8

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.example.lab8.databinding.FragmentTask2Binding

class Task2Fragment : Fragment() {
    private lateinit var binding: FragmentTask2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTask2Binding.inflate(inflater)

        val customView: CanvasView = CanvasView()
        customView.layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        binding.root.addView(customView)

        return binding.root
    }

    private inner class CanvasView : View(requireContext()) {
        private var bitmapX: Float = 0f
        private var bitmapY: Float = 0f
        private var bitmapWidth: Int = 0
        private var bitmapHeight: Int = 0
        private var bitmapDirectionX: Int = 0
        private var bitmapDirectionY: Int = 0
        private var pacman: Bitmap = with(ResourcesCompat.getDrawable(resources, R.drawable.pacman, null)!!) {
            bitmapWidth = intrinsicWidth
            bitmapHeight = intrinsicHeight
            toBitmap(bitmapWidth, bitmapHeight)}/*.let {
            Bitmap.createBitmap(it, 0, 0, it.width, it.height,
                null, true) }*/
        private val paint: Paint = Paint()
        private val bitmapMoveX: Int = 9
        private val bitmapMoveY: Int = 3

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val width: Float = width.toFloat()
            val height: Float = height.toFloat()

            canvas.drawBitmap(pacman, bitmapX, bitmapY, paint)

            if (bitmapDirectionX == 0) {
                if (bitmapX + bitmapWidth + bitmapMoveX <= width) bitmapX += bitmapMoveX else {
                    bitmapDirectionX = 1
                    pacman = Bitmap.createBitmap(pacman, 0, 0, bitmapWidth, bitmapHeight,
                        Matrix().apply { preScale(-1f, 1f) }, false)
                    bitmapX -= bitmapMoveX
                }
            } else {
                if (bitmapX >= bitmapMoveX) bitmapX -= bitmapMoveX else {
                    bitmapDirectionX = 0
                    pacman = Bitmap.createBitmap(pacman, 0, 0, bitmapWidth, bitmapHeight,
                        Matrix().apply { preScale(-1f, 1f) }, false)
                    bitmapX += bitmapMoveX
                }
            }

            if (bitmapDirectionY == 0) {
                if (bitmapY + bitmapHeight + bitmapMoveY <= height) bitmapY += bitmapMoveY else {
                    bitmapDirectionY = 1
                    pacman = Bitmap.createBitmap(pacman, 0, 0, bitmapWidth, bitmapHeight,
                        Matrix().apply { preScale(1f, -1f) }, false)
                    bitmapY -= bitmapMoveY
                }
            } else {
                if (bitmapY >= bitmapMoveY) bitmapY -= bitmapMoveY else {
                    bitmapDirectionY = 0
                    pacman = Bitmap.createBitmap(pacman, 0, 0, bitmapWidth, bitmapHeight,
                        Matrix().apply { preScale(1f, -1f) }, false)
                    bitmapY += bitmapMoveY
                }
            }

            invalidate()
        }
    }
}