Labyrinth
=========

This library creates a true labyrinth using low-memory, cpu-intensive hunt'n'seek algorythm. 

Program
-------

### Build

```
sbt package

scala -cp target/scala-2.11/labyrinth_2.11-0.0.1.jar Labyrinth [cols=10 [rows=cols [times=1 [concurrency=num_cpus]]]]
```

### Create

```
$ scala -cp target/scala-2.11/labyrinth_2.11-0.0.1.jar Labyrinth

┏━━━━━━━┳━━━━━━━━━┳━┓
┃ ╺━┳━╸ ┃ ┏━━━━━┓ ╹ ┃
┣━━━┫ ╺━┻━┛ ┏━╸ ┃ ╺━┫
┃ ╻ ┗━━━━━┳━┛ ┏━┻━┓ ┃
┃ ┃ ┏━╸ ╻ ┃ ╻ ┃ ╻ ╹ ┃
┃ ┗━┫ ╺━┻━┛ ┗━┫ ┗━━━┫
┣━╸ ┣━╸ ┏━━━┓ ╹ ┏━━━┫
┃ ┏━┛ ┏━┻━┓ ┗━┳━┛ ╻ ┃
┃ ┗━┓ ┃ ╻ ┃ ╻ ╹ ┏━┫ ┃
┃ ╻ ┃ ╹ ┃ ┗━┻━╸ ┃ ╹ ┃
┗━┻━┻━━━┻━━━━━━━┻━━━┛

$ scala -cp target/scala-2.11/labyrinth_2.11-0.0.1.jar Labyrinth 40 5

┏━━━━━┳━┳━━━━━━━━━┳━━━━━┳━━━┳━━━━━┳━━━━━┳━━━━━┳━┳━━━━━┳━━━━━━━━━━━━━━━┳━━━━━┳━━━┓
┃ ┏━╸ ┃ ┃ ╺━┓ ╺━┳━┛ ┏━┓ ╹ ╺━┫ ╺━┓ ╹ ┏━╸ ┃ ╺━┓ ┃ ┣━━━┓ ╹ ┏━━━━━━━┳━━━┓ ┗━━━┓ ╹ ╻ ┃
┃ ┣━━━┛ ┃ ╻ ┣━╸ ┃ ╺━┫ ┗━━━┓ ╹ ┏━┻━┓ ┣━━━┻━┓ ┃ ╹ ┃ ╻ ┗━┳━┫ ┏━━━╸ ┃ ╺━┻━━━┓ ┃ ╺━┫ ┃
┃ ╹ ╻ ╺━╋━┛ ┃ ╺━┫ ╻ ╹ ┏━┓ ┗━┳━┛ ╻ ╹ ┃ ╻ ╻ ┃ ┗━━━┫ ┗━┓ ╹ ┃ ┃ ╺━━━┻━━━┳━╸ ┃ ┣━╸ ┃ ┃
┣━━━┛ ╻ ╹ ╺━┻━┓ ╹ ┣━╸ ┃ ┗━╸ ┃ ╺━┻━━━┛ ┃ ┗━┛ ╺━┓ ╹ ╻ ┗━┓ ╹ ┃ ╺━━━┓ ╺━┛ ╻ ╹ ╹ ┏━┛ ┃
┗━━━━━┻━━━━━━━┻━━━┻━━━┻━━━━━┻━━━━━━━━━┻━━━━━━━┻━━━┻━━━┻━━━┻━━━━━┻━━━━━┻━━━━━┻━━━┛
```


Library
-------

Add to `project/Build.scala`:

```
import sbt._

object MyBuild extends Build {
 
  lazy val root = Project("root", file(".")) dependsOn(labyrinthProject)
  lazy val labyrinthProject = RootProject(uri("git://github.com/royaltm/scala-labyrinth.git"))
 
}
```

Put to `HelloLabyrinth.scala`:

```scala
import labyrinth.{Wall, Direction}

object HelloLabyrinth extends App {
    val wall = Wall(20, 20)
    wall.carve
    wall.print
    wall.open(0, 10, Direction.Up)
    assert(true == wall.isOpen(0, 10, Direction.Up))
    wall.close(0, 10, Direction.Up)
    assert(false == wall.isOpen(0, 10, Direction.Up))
}
```

Run `sbt run`.
