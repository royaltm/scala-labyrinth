import org.scalatest._

import labyrinth._
import labyrinth.Direction._

class WallCarveSpec extends FunSuite with Matchers {

    def count_open(labyrinth: Wall) = {
        var opened = 0
        for (y <- 0 until labyrinth.rows) {
            for (x <- 0 until labyrinth.cols) {
                if (labyrinth.isOpen(x, y, Up))    { opened+= 1 }
                if (labyrinth.isOpen(x, y, Right)) { opened+= 1 }
                if (labyrinth.isOpen(x, y, Left))  { opened+= 1 }
                if (labyrinth.isOpen(x, y, Down))  { opened+= 1 }
            }
        }
        opened
    }

    test("should carve a labyrinth") {
        val labyrinth = Wall(137, 111)
        count_open(labyrinth) shouldBe 0
        labyrinth.carve()
        count_open(labyrinth) shouldBe 137*111*2-2
        labyrinth.closeAll()
        count_open(labyrinth) shouldBe 0
        labyrinth.carve()
        count_open(labyrinth) shouldBe 137*111*2-2
    }
}
