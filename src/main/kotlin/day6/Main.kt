package day6

import java.io.File

fun main() {
  val grid = File("src/main/kotlin/day6/input.txt")
    .readLines()
    .map { line -> line.map { char -> char } }
  var x = 0
  var y = 0
  grid.forEachIndexed { yInd, line ->
    line.forEachIndexed { xInd, char ->
      if (char == '^') {
        x = xInd
        y = yInd
      }
    }
  }
  val total = move(grid, x, y, Direction.Up, mutableSetOf()).distinctBy { it.x to it.y }
  println("part one: ${total.size}")
  // part one 5067

  var exceptions = 0
  for (i in 0..grid.lastIndex) {
    for (j in 0..grid.lastIndex) {
      val gridWithBlock = gridWithBlock(grid, i, j)
      try {
        move(gridWithBlock, x, y, Direction.Up, mutableSetOf())
      } catch (e: Exception) {
        exceptions++
      }
    }
  }
  println(exceptions)
  // part two 1793
}

fun gridWithBlock(grid: List<List<Char>>, x: Int, y: Int): List<List<Char>> {
  val mut = grid.map { it.toMutableList() }.toMutableList()
  if (mut[x][y] == '^') return grid
  mut[x][y] = '#'
  return mut
}

tailrec fun move(grid: List<List<Char>>, x: Int, y: Int, direction: Direction, positions: MutableSet<Position>): MutableSet<Position> {
  val (nextX, nextY) = nextCoords(direction, x, y)
  return if (nextX < 0 || nextX > grid.lastIndex || nextY < 0 || nextY > grid.lastIndex) {
//    positions.add(Pair(x, y))
    val newPos = Position(x, y, direction)
    if (positions.contains(newPos)) throw Exception()
    positions.add(Position(x, y, direction))
    positions
  }
  else {
    val nextChar = grid[nextY][nextX]
    if (nextChar == '#') {
      val newDirection = when (direction) {
        Direction.Up -> Direction.Right
        Direction.Down -> Direction.Left
        Direction.Left -> Direction.Up
        Direction.Right -> Direction.Down
      }
      move(grid, x, y, newDirection, positions)
    } else {
//      positions.add(Pair(x, y))
      val newPos = Position(x, y, direction)
      if (positions.contains(newPos)) throw Exception()
      positions.add(Position(x, y, direction))
      move(grid, nextX, nextY, direction, positions)
    }
  }
}

fun nextCoords(direction: Direction, x: Int, y: Int): Pair<Int, Int> {
  return when (direction) {
    Direction.Up -> {
      x to y - 1
    }
    Direction.Down -> {
      x to y + 1
    }
    Direction.Left -> {
      x - 1 to y
    }
    Direction.Right -> {
      x + 1 to y
    }
  }
}

enum class Direction {
  Up, Down, Left, Right
}

data class Position(val x: Int, val y: Int, val direction: Direction)
