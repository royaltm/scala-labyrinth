package labyrinth.wall

import scala.util.control.Breaks.{breakable, break}
import scala.util.Random

import labyrinth.room._
import labyrinth.direction.Direction._
import labyrinth.direction.{Move => Mov}

trait WallCarve extends WallBase {
    /// Carve a fresh labyrinth using hunt and seek algorithm.
    ///
    /// The labyrinth must be in an initial state otherwise this method will never return.
    def carve() : Unit = {
        val rooms = Room(rows, cols)
        val rnd = (x: Int) => Random.nextInt(x)
        val mov = Mov.fromDir(Up)
        var (x, y) = randomStart(rnd)
        val outbound_or_marked = (x: Int, y: Int, mov: Mov) => {
            val (x0, y0) = (x + mov.dx, y + mov.dy)
            !inBounds(x0, y0) || rooms.wasMarked(x0, y0)
        }

        rooms.mark(x, y)

        /* hunt */
        for (_ <- 1 until rows * cols) {
            rnd(3) match {
                case 1 => mov.turnRt()
                case 2 => mov.turnLt()
                case _ => {}
            }
            breakable {
                while (outbound_or_marked(x, y, mov)) {
                    for (_ <- 0 until 3) {
                        mov.turnRt()
                        if (!outbound_or_marked(x, y, mov)) break
                    }
                    /* seek */
                    do {
                        x += 1
                        y += 1
                        if (!inBounds(x, y)) {
                            val (a, b) = randomStart(rnd)
                            x = a;
                            y = b;
                        }
                    } while(!rooms.wasMarked(x, y))
                }
            }
            open(x, y, mov.toDir())
            x += mov.dx;
            y += mov.dy;
            rooms.mark(x, y)
        }
    }
}
