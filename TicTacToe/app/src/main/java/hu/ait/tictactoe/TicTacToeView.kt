package hu.ait.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paintBackgorund: Paint

    init {
        paintBackgorund = Paint()
        paintBackgorund.color = Color.BLACK
        paintBackgorund.style = Paint.Style.FILL
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), paintBackgorund)
    }

}