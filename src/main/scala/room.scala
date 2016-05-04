package labyrinth.room

import labyrinth.vector2d.Vec2D

class Room(rows: Int, cols: Int) extends Vec2D[Boolean](cols, rows, false) {
    def mark(x: Int, y: Int) = set(x, y, true)
    def wasMarked(x: Int, y: Int) = get(x, y)
}

object Room {
    def apply(rows: Int, cols: Int) = new Room(rows, cols)
}
