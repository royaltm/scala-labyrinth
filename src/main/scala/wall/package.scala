package labyrinth.wall

import labyrinth.vector2d.Vec2D
import labyrinth.direction.Direction
import labyrinth.direction.Direction._

abstract class WallBase(val cols: Int, val rows: Int) {
    def isOpen(x: Int, y: Int, dir: Direction) : Boolean;
    def open(x: Int, y: Int, dir: Direction) : Wall;
    def close(x: Int, y: Int, dir: Direction) : Wall;
    def inBounds(x: Int, y: Int) : Boolean;
    def randomStart(rnd: (Int) => Int) : Tuple2[Int, Int];
}

class Wall(cols: Int, rows: Int, exits: Vec2D[Boolean])
            extends WallBase(cols, rows) with WallPrint with WallCarve {

    private def exitAt(x: Int, y: Int, dir: Direction) = {
        val (dx, dy) = dir match {
            case Up    => (1, 0)
            case Right => (2, 0)
            case Down  => (1, 1)
            case Left  => (0, 0)
        }
        (x * 2 + dx, y + dy)
    }
    /** Create a new labyrinth with all exits being closed.
      *
      * ==Examples==
      * {{{
      * import labyrinth.Wall
      * val labyrinth = new Wall(5, 2)
      * assert(5 == labyrinth.cols)
      * assert(2 == labyrinth.rows)
      * }}}
      */
    def this(cols: Int, rows: Int) = this(
        cols,
        rows,
        Vec2D(cols * 2 + 1, rows + 1, false)
    )

    /** Close all of the labyrinth exits bringing it to the initial state.
      * This is required to reuse the instance for another carving.
      *
      * ==Examples==
      *
      * {{{
      * import labyrinth.Wall
      *
      * val labyrinth = Wall(10, 10)
      * labyrinth.carve
      * labyrinth.closeAll.carve
      * }}}
      */
    def closeAll() : Wall = {
        exits.fill(false);
        this
    }
    /** Check existence of the specified exit.
      *
      * ==Examples==
      *
      * {{{
      * import labyrinth.{Wall, Direction}
      *
      * val labyrinth = Wall(10, 10);
      * assert(false == labyrinth.isOpen(0, 0, Direction.Right))
      * }}}
      */
    override def isOpen(x: Int, y: Int, dir: Direction) = {
        val (x0, y0) = exitAt(x, y, dir);
        exits.get(x0, y0)
    }
    /** Set existence of the specified exit.
      *
      * ==Examples==
      *
      * {{{
      * import labyrinth.{Wall, Direction}
      *
      * val labyrinth = Wall(10, 10)
      * assert(false == labyrinth.isOpen(4, 5, Direction.Up))
      * labyrinth.set(4, 5, Direction.Up, true)
      * assert(true == labyrinth.isOpen(4, 5, Direction.Up))
      * }}}
      */
    def set(x: Int, y: Int, dir: Direction, `val`: Boolean) : Wall = {
        val (x0, y0) = exitAt(x, y, dir);
        exits.set(x0, y0, `val`);
        this
    }
    /** Open specified exit.
      *
      * ==Examples==
      *
      * {{{
      * use labyrinth::{Wall, Direction}
      *
      * let mut labyrinth = Wall::new(10, 10)
      * labyrinth.open(2, 3, Direction::Down)
      * assert(true == labyrinth.isOpen(2, 3, Direction::Down))
      * labyrinth.close(2, 3, Direction::Down)
      * assert(false == labyrinth.isOpen(2, 3, Direction::Down))
      * }}}
      */
    override def open(x: Int, y: Int, dir: Direction) : Wall = {
        set(x, y, dir, true)
    }
    /** Close specified exit. */
    override def close(x: Int, y: Int, dir: Direction) : Wall = {
        set(x, y, dir, false)
    }
    /** Check if x and y coordinates are valid in a labyrinth.
      *
      * ==Examples==
      *
      * {{{
      * import labyrinth.Wall
      *
      * val labyrinth = Wall(3, 2)
      * assert(true == labyrinth.inBounds(2, 1))
      * assert(false == labyrinth.inBounds(4, 5))
      * assert(false == labyrinth.inBounds(-1, 0))
      * assert(false == labyrinth.inBounds(-4, -5))
      * assert(false == labyrinth.inBounds(1, -1))
      * }}}
      */
    override def inBounds(x: Int, y: Int) : Boolean = {
        x >= 0 && y >= 0 && x < cols && y < rows
    }

    /** Randomize coordinates.
      *
      * The `rnd(n: Int) -> Int` should return a random number between `0` and `n` excluding `n`.
      */
    override def randomStart(rnd: (Int) => Int) = {
        (rnd(cols), rnd(rows))
    }
}

object Wall {
    def apply(cols: Int, rows: Int) = new Wall(cols, rows)
}
