import scala.util.Try
import collection.mutable.ArrayBuffer
import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}
import java.util.concurrent.{Executors, ExecutorService}

import labyrinth._

object Labyrinth extends App {
    implicit class ArrayBufferShifter(args: ArrayBuffer[String]) {
        def shift() = {
            val arg = args.headOption
            arg match { case Some(_) => args.remove(0) case _ => {} }
            arg
        }

    }

    var bargs = args.to[ArrayBuffer]
    val cols = bargs.shift match { case Some(arg) => Try(arg.toInt).toOption case _ => Some(10) }
    val rows = bargs.shift match { case Some(arg) => Try(arg.toInt).toOption case _ => cols }
    val deep = bargs.shift match { case Some(arg) => Try(arg.toInt).toOption case _ => Some(1) }
    val cpus = bargs.shift match { case Some(arg) => Try(arg.toInt).toOption
                                          case _ => Some(Runtime.getRuntime().availableProcessors()) }

    (rows, cols, deep, cpus) match {
        case (Some(rows), Some(cols), Some(deep), Some(cpus)) => {
            if (rows > 0 && cols > 0) {

                val concurrency = if (cpus > deep) deep else cpus

                if (concurrency <= 1) {
                    /* Linear implementation */
                    for (_ <- 0 until deep) {
                        val wall = Wall(cols, rows)
                        wall.carve
                        wall.print
                    }
                } else {
                    /* Parallel implementation */
                    parallel(cols, rows, deep, concurrency);
                }

            } else {
                println("Hey, give me numbers bigger than 0")
            }
        }
        case _ => println("Hey, give me a number.")
    }

    def parallel(cols: Int, rows: Int, deep: Int, concurrency: Int) {
        val pool: ExecutorService = Executors.newFixedThreadPool(concurrency)
        val queue = new LinkedBlockingQueue[Wall]()
        try {
            for (_ <- 0 until deep) {
                pool.execute(new Runnable {
                    def run {
                        val wall = Wall(cols, rows)
                        wall.carve
                        queue.put(wall)
                    }
                })
            }
            for (_ <- 0 until deep) {
                val wall = queue.take()
                wall.print
            }
        } finally {
            pool.shutdown()
        }
    }

}
