package labyrinth.direction

sealed abstract class Direction

object Direction {
    case object Up extends Direction
    case object Right extends Direction
    case object Down extends Direction
    case object Left extends Direction
}

import Direction._

class Move(var dx: Int, var dy: Int) {
    def toDir() =
        (dx, dy) match {
            case ( 0,  1) => Down
            case (-1,  0) => Left
            case ( 1,  0) => Right
            case ( 0, -1) => Up
            case _ => throw new Exception(s"Invalid Move value $dx, $dy");
        }
    def back() = { this.dx = -dx; this.dy = -dy }
    def turnRt() = (dx, dy) match { case (dx, dy) => { this.dx = -dy; this.dy =  dx; } }
    def turnLt() = (dx, dy) match { case (dx, dy) => { this.dx =  dy; this.dy = -dx; } }
}

object Move {
    private def up() = new Move( 0, -1)
    private def rt() = new Move( 1,  0)
    private def dn() = new Move( 0,  1)
    private def lt() = new Move(-1,  0)
    def fromDir(dir: Direction) =
        dir match {
            case Up      => Move.up()
            case Right   => Move.rt()
            case Down    => Move.dn()
            case Left    => Move.lt()
        }
}
