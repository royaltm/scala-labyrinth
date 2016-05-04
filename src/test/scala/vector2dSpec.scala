import org.scalatest._
import labyrinth.vector2d.Vec2D

class Vec2DSpec extends FunSuite with Matchers {

    test("should create an empty vector") {
        val v2d = Vec2D(0, 0, 0)
        v2d.numCols shouldBe 0
        v2d.numRows shouldBe 0
    }

    test("should create vector with a value") {
        val v2d = Vec2D(3, 2, 'x')
        v2d.numCols shouldBe 3
        v2d.numRows shouldBe 2
        for(row <- 0 until 2) {
            for(col <- 0 until 3) {
                v2d.get(col, row) shouldBe 'x'
            }
        }
        v2d.set(1, 0, 'a');
        v2d.get(1, 0) shouldBe 'a'
        v2d.get(0, 1) shouldBe 'x'
    }

    test("should fill vector with a value") {
        val v2d = Vec2D(4, 8, '@')
        v2d.numCols shouldBe 4
        v2d.numRows shouldBe 8
        for(row <- 0 until 8) {
            for(col <- 0 until 4) {
                v2d.get(col, row) shouldBe '@'
            }
        }
        v2d.fill('!');
        for(row <- 0 until 8) {
            for(col <- 0 until 4) {
                v2d.get(col, row) shouldBe '!'
            }
        }

    }

}
