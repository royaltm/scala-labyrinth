package labyrinth.vector2d;

import scala.collection.mutable.ArrayBuffer

/** Create two-dimensional vector containing `rows` vectors of `cols` size each.
**/
class Vec2D[T](cols: Int, val numRows: Int, value: T) {

    private val rows = Array.tabulate(numRows)(_ => ArrayBuffer.fill(cols)(value))

    def fill(value: T) {
        for(row <- rows) {
            for (x <- row.indices) {
                row(x) = value
            }
        }
    }
    def numCols() = cols
    def set(col: Int, row: Int, value: T) = {
        rows(row)(col) = value
    }
    def get(col: Int, row: Int) = rows(row)(col)
}

object Vec2D {
    def apply[T](cols: Int, rows: Int, value: T) = new Vec2D[T](cols, rows, value)
}
