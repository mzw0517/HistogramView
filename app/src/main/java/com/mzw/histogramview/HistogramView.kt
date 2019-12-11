package com.mzw.histogramview

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*

class HistogramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var linePaint: Paint
    private var rectPaint: Paint
    private var textPaint: Paint
    private var path: Path
    private val strs = arrayOf("Froyo", "GB", "ICS", "JB", "KitKat", "L", "M")
    private var random: Random
    private val PADDING_LEFT = 120
    private val WIDTH = 90

    init {
        linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint.setStyle(Paint.Style.STROKE)
        linePaint.setColor(Color.WHITE)
        linePaint.setStrokeWidth(4f)

        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        rectPaint.setColor(Color.GREEN)

        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint.setColor(Color.WHITE)
        textPaint.setTextSize(22f)
        textPaint.setTextAlign(Paint.Align.CENTER)

        path = Path()
        path.moveTo(100f, 100f)
        path.lineTo(100f, 600f)
        path.rLineTo(900f, 0f)

        random = Random()

        val valueAnimator = ValueAnimator.ofInt(0, 10)
        valueAnimator.repeatMode = INFINITE
        valueAnimator.duration = 10000
        valueAnimator.addUpdateListener {
            Log.d("----->", "onAnimationUpdate: ")
            start()
        }

        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, linePaint)

        for (i in strs.indices) {

            canvas.drawRect(
                (PADDING_LEFT + WIDTH * i).toFloat(),
                random.nextInt(400).toFloat(),
                (200 + WIDTH * i).toFloat(),
                600f,
                rectPaint
            )
            canvas.drawText(strs[i], (160 + 90 * i).toFloat(), 620f, textPaint)
        }
    }

    fun start() {
        invalidate()
    }
}