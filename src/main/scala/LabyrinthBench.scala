import scala.util.Try
import java.text.NumberFormat

import labyrinth._

object LabyrinthBench extends App {
  def time(iter: Int)(block: => Unit): Unit = {
      val t0 = System.nanoTime()
      for(_ <- 0 until iter) {
        val result = block
      }
      val ns = NumberFormat.getIntegerInstance().format((System.nanoTime() - t0) / iter)
      println(s"Elapsed time: $ns ns/iter")
  }

  val iterOpt = args.headOption match { case Some(arg) => Try(arg.toInt).toOption case _ => None }
  val iter = iterOpt match { case Some(iter) => iter case _ => 1000 }

  {
    val wall = Wall(10, 10)
    println(s"Iterating: $iter 10x10")
    time(iter) {
      wall.closeAll.carve
      wall.toString
    }
  }

  {
    val wall = Wall(20, 20)
    println(s"Iterating: $iter 20x20")
    time(iter) {
      wall.closeAll.carve
      wall.toString
    }
  }

  {
    val wall = Wall(50, 50)
    println(s"Iterating: $iter 50x50")
    time(iter) {
      wall.closeAll.carve
      wall.toString
    }
  }

  {
    val wall = Wall(100, 100)
    println(s"Iterating: $iter 100x100")
    time(iter) {
      wall.closeAll.carve
      wall.toString
    }
  }
}
