package com.example.lab8

import android.graphics.Color
import android.graphics.Paint

internal data class Particle (
    var radius: Float = 0f,
    var x: Float = 0f,
    var y: Float = 0f,
    var velX: Float = 0f,
    var velY: Float = 0f,
    var accX: Float = 0f,
    var accY: Float = 0f,
    var paint: Paint = Paint().apply { color = Color.BLACK }
) {}