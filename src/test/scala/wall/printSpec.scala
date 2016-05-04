import org.scalatest._

import labyrinth._
import labyrinth.Direction._

class WallPrintSpec extends FunSuite {

    test("should draw a labyrinth") {
        val labyrinth = Wall(2, 2)
        assert(
"""┏━┳━┓
┣━╋━┫
┗━┻━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 0, Right)
        assert(
"""┏━━━┓
┣━┳━┫
┗━┻━┛
"""     == labyrinth.toString)
        labyrinth.open(1, 0, Down)
        assert(
"""┏━━━┓
┣━┓ ┃
┗━┻━┛
"""     == labyrinth.toString)
        labyrinth.open(1, 1, Left)
        assert(
"""┏━━━┓
┣━╸ ┃
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 1, Up)
        assert(
"""┏━━━┓
┃   ┃
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 0, Left)
        assert(
"""╺━━━┓
╻   ┃
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 0, Up)
        assert(
"""  ╺━┓
╻   ┃
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(1, 0, Up)
        assert(
"""    ╻
╻   ┃
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(1, 0, Right)
        assert(
"""     
╻   ╻
┗━━━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 1, Left)
        assert(
"""     
    ╻
╺━━━┛
"""     == labyrinth.toString)
        labyrinth.open(0, 1, Down)
        assert(
"""     
    ╻
  ╺━┛
"""     == labyrinth.toString)
        labyrinth.open(1, 1, Down)
        assert(
"""     
    ╻
    ╹
"""     == labyrinth.toString)
        labyrinth.open(1, 1, Right)
        assert(
"""     
     
     
"""     == labyrinth.toString)

    }

}