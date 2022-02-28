package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.ait.tictactoe.MainActivity
import hu.ait.tictactoe.R
import hu.ait.tictactoe.model.TicTacToeModel

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paintBackgorund: Paint
    private lateinit var paintLine: Paint
    private lateinit var paintText: Paint

    private var bitmapBg: Bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.grass)

    private var bitmapFlag: Bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.flag)

    init {
        paintBackgorund = Paint()
        paintBackgorund.color = Color.BLACK
        paintBackgorund.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintText = Paint()
        paintText.color = Color.GREEN
        paintText.textSize = 70f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paintText.textSize = h/3f

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg,
            width, height, false)

        bitmapFlag = Bitmap.createScaledBitmap(bitmapFlag,
            width/3, height/3, false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), paintBackgorund
        )

        //canvas?.drawBitmap(bitmapBg, 0f, 0f, null)

        drawGameArea(canvas!!)

        drawPlayers(canvas!!)

        canvas?.drawText(
            "5", 0f, height/3f, paintText
        )

        canvas?.drawBitmap(bitmapFlag, width/3f, height/3f, null)
    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // two horizontal lines
        canvas.drawLine(0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLine)
        canvas.drawLine(0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLine)

        // two vertical lines
        canvas.drawLine((width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLine)
        canvas.drawLine((2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLine)
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLine)

                    canvas.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine)
                }
            }
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {

            val tX = event.x.toInt() / (width / 3)
            val tY = event.y.toInt() / (height / 3)

            if ((context as MainActivity).isFlagModeOn()) {

            } else {

            }



            if (tX < 3 && tY < 3 && TicTacToeModel.getFieldContent(tX, tY) ==
                TicTacToeModel.EMPTY) {
                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())
                TicTacToeModel.changeNextPlayer()

                if (TicTacToeModel.getNextPlayer() == TicTacToeModel.CIRCLE){
                    (context as MainActivity).showText("Next player is O")
                } else  {
                    (context as MainActivity).showText("Next player is X")
                }


                if (TicTacToeModel.getWinner() == TicTacToeModel.CIRCLE) {
                    (context as MainActivity).showText("Winner is O")
                    // (MainActivity)context
                }

                invalidate()
            }

        }

        return true
    }

    public fun resetGame() {
        TicTacToeModel.resetModel()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

}