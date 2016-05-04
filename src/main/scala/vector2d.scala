package labyrinth.vector2d;

import scala.collection.mutable.ArrayBuffer

/** Create two-dimensional vector containing `rows` vectors of `cols` size each.
**/
class Vec2D[T](cols: Int, val num_rows: Int, value: T) {

    private val rows = Array.tabulate(num_rows)(_ => ArrayBuffer.fill(cols)(value))

    def fill(value: T) {
        for(row <- rows) {
            for (x <- row.indices) {
                row(x) = value
            }
        }
    }
    def num_cols() = cols
    def set(col: Int, row: Int, value: T) = {
        rows(row)(col) = value
    }
    def get(col: Int, row: Int) = rows(row)(col)
}

object Vec2D {
    def apply[T](cols: Int, rows: Int, value: T) = new Vec2D[T](cols, rows, value)
}
