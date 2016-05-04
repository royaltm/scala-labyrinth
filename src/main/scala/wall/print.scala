package labyrinth.wall

import java.io.{PrintWriter, BufferedWriter, OutputStreamWriter}

import labyrinth.direction.Direction._

trait WallPrint extends WallBase {
    /// Print a labyrinth to stdout using UNICODE BOX characters.
    def print() : Unit = {
        val out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8")))
        def writer = (s: String) => out.print(s)
        draw(writer)
        out.flush
    }
    /// Draw a labyrinth as a String using UNICODE BOX characters.
    override def toString() : String = {
        val buf = new StringBuilder
        draw((s: String) => buf ++= s)
        buf.toString
    }
    /// Draw a labyrinth using UNICODE BOX characters to a `writer`.
    def draw(writer: (String) => Unit) : Unit = {
        for (y <- 0 until rows) {
            for (x <- 0 until cols) {
                if (x == 0 || isOpen(x - 1, y, Up)) {
                    if (y == 0 || isOpen(x, y - 1, Left)) {
                        if (isOpen(x, y, Up)) {
                            writer(if (isOpen(x, y, Left)) "  " else "╻ ")
                        } else {
                            writer(if (isOpen(x, y, Left)) "╺━" else "┏━")
                        }
                    } else {
                        if (isOpen(x, y, Up)) {
                            writer(if (isOpen(x, y, Left)) "╹ " else "┃ ")
                        } else {
                            writer(if (isOpen(x, y, Left)) "┗━" else "┣━")
                        }
                    }
                } else {
                    if (y == 0 || isOpen(x, y - 1, Left)) {
                        if (isOpen(x, y, Up)) {
                            writer(if (isOpen(x, y, Left)) "╸ " else "┓ ")
                        } else {
                            writer(if (isOpen(x, y, Left)) "━━" else "┳━")
                        }
                    } else {
                        if (isOpen(x, y, Up)) {
                            writer(if (isOpen(x, y, Left)) "┛ " else "┫ ")
                        } else {
                            writer(if (isOpen(x, y, Left)) "┻━" else "╋━")
                        }
                    }
                }
            }
            val x = cols
            if (isOpen(x - 1, y, Up)) {
                if (y == 0 || isOpen(x, y - 1, Left)) {
                    writer(if (isOpen(x, y, Left)) " " else "╻")
                } else {
                    writer(if (isOpen(x, y, Left)) "╹" else "┃")
                }
            } else {
                if (y == 0 || isOpen(x, y - 1, Left)) {
                    writer(if (isOpen(x, y, Left)) "╸" else "┓")
                } else {
                    writer(if (isOpen(x, y, Left)) "┛" else "┫")
                }
            }
            writer("\n")
        }
        val y = rows
        for (x <- 0 until cols) {
            if (x == 0 || isOpen(x - 1, y, Up)) {
                if (isOpen(x, y - 1, Left)) {
                    writer(if (isOpen(x, y, Up)) "  " else "╺━")
                } else {
                    writer(if (isOpen(x, y, Up)) "╹ " else "┗━")
                }
            } else {
                if (y == 0 || isOpen(x, y - 1, Left)) {
                    writer(if (isOpen(x, y, Up)) "╸ " else "━━")
                } else {
                    writer(if (isOpen(x, y, Up)) "┛ " else "┻━")
                }
            }
        }
        val x = cols
        if (isOpen(x - 1, y, Up)) {
            writer(if (isOpen(x, y - 1, Left)) " " else "╹")
        } else {
            writer(if (isOpen(x, y - 1, Left)) "╸" else "┛")
        }
        writer("\n")
    }
}
