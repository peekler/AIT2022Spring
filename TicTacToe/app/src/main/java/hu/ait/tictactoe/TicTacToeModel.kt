package hu.ait.tictactoe

object TicTacToeModel {

    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2

    public val TIE: Short = 3
    public val NOTOVER: Short = 4

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY))

    private var nextPlayer = CIRCLE

    fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }


    public fun getWinner() : Short {
        return NOTOVER
    }

}