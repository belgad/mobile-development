package com.belgad.paint

import android.content.res.ColorStateList
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.belgad.paint.databinding.ActivityMainBinding
import com.belgad.paint.databinding.DialogSettingsBinding
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myCanvas: MyCanvas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        myCanvas = MyCanvas()
        binding.canvasLayout.addView(myCanvas)
        binding.clearCanvasButton.setOnClickListener {
            myCanvas.canvas.drawColor(Color.WHITE)
            myCanvas.invalidate()
        }
        binding.penChangeButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogBinding = DialogSettingsBinding.inflate(layoutInflater)
            val currentColor = myCanvas.paint.color

            val density = 2f
            var size = (myCanvas.paint.strokeWidth * density).toInt()
            var red = (currentColor and 0xFF0000) shr 16
            var green = (currentColor and 0xFF00) shr 8
            var blue = currentColor and 0xFF

            dialogBinding.redSeekbar.progress = red
            dialogBinding.greenSeekbar.progress = green
            dialogBinding.blueSeekbar.progress = blue
            dialogBinding.sizeSeekbar.progress = myCanvas.paint.strokeWidth.toInt()
            dialogBinding.penPreview.layoutParams = FrameLayout.LayoutParams(size, size).apply {
                gravity = Gravity.CENTER
            }
            dialogBinding.penPreview.backgroundTintList =
                ColorStateList.valueOf((red shl 16) + (green shl 8) + blue).withAlpha(255)

            dialogBinding.redSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    red = progress
                    dialogBinding.penPreview.backgroundTintList =
                        ColorStateList.valueOf((red shl 16) + (green shl 8) + blue).withAlpha(255)
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            dialogBinding.greenSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    green = progress
                    dialogBinding.penPreview.backgroundTintList =
                        ColorStateList.valueOf((red shl 16) + (green shl 8) + blue).withAlpha(255)
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            dialogBinding.blueSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    blue = progress
                    dialogBinding.penPreview.backgroundTintList =
                        ColorStateList.valueOf((red shl 16) + (green shl 8) + blue).withAlpha(255)
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            dialogBinding.sizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    size = (density * progress).toInt()
                    dialogBinding.penPreview.layoutParams = FrameLayout.LayoutParams(size, size).apply {
                        gravity = Gravity.CENTER
                    }
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            builder.setView(dialogBinding.root)
                .setTitle("Pen settings")
                .setPositiveButton("Apply") { _, _ ->
                    val color = (red shl 16) + (green shl 8) + blue
                    myCanvas.paint.strokeWidth = size.toFloat() / density
                    myCanvas.paint.color = (color + 0xFF000000).toInt()
                    binding.penPreview.text = dialogBinding.sizeSeekbar.progress.toString()
                    if (Color.luminance(color) > 0.5) binding.penPreview.setTextColor(Color.BLACK)
                    else binding.penPreview.setTextColor(Color.WHITE)
                    binding.penPreview.backgroundTintList = ColorStateList.valueOf(myCanvas.paint.color)
                }
                .show()
        }

        setContentView(binding.root)
    }

    private inner class MyCanvas : View(this) {
        private val touchTolerance = /*ViewConfiguration.get(context).scaledEdgeSlop*/15
        lateinit var canvas: Canvas
        private lateinit var bitmap: Bitmap
        var paint: Paint = Paint().apply {
            color = Color.BLACK
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeWidth = 6f
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
        private var path: Path = Path()
        private var motionX = 0f
        private var motionY = 0f
        private var currentX = 0f
        private var currentY = 0f

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            super.onSizeChanged(w, h, oldw, oldh)
            if (::bitmap.isInitialized) bitmap.recycle()
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap)
            canvas.drawColor(Color.WHITE)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            canvas?.drawBitmap(bitmap, 0f, 0f, null)
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            event?.also {
                motionX = it.x
                motionY = it.y
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        path.reset()
                        path.moveTo(motionX, motionY)
                        currentX = motionX
                        currentY = motionY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = abs(motionX - currentX)
                        val dy = abs(motionY - currentY)
                        if ((dx >= touchTolerance) and (dy >= touchTolerance)) {
                            path.quadTo(currentX, currentY, (motionX + currentX) / 2, (motionY + currentY) / 2)
                            currentX = motionX
                            currentY = motionY
                            canvas.drawPath(path, paint)
                            invalidate()
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        path.quadTo(currentX, currentY, motionX, motionY)
                        canvas.drawPath(path, paint)
                        invalidate()
                        path.reset()
                    }
                }
            }
            return true
        }
    }
}