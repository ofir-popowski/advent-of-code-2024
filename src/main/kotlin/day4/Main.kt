package day4

import java.io.File

fun main() {
  val input = File("src/main/kotlin/day4/input.txt").readText()
  val matrix = input.lines().map { line ->
    line.map { it }
  }
  val rightLeft = input.lines().sumOf { line ->
    line.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  val upDown = matrix.indices.sumOf { index ->
    getColumn(matrix, index).concatToString().windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  // since we start at index 0,0 and moving "right"
  val diagonalUpper = matrix.indices.sumOf { index ->
    var start = index
    var diagonal = ""
    matrix.indices.forEach { i ->
      runCatching { diagonal += (matrix[i][start++]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  // skipping 0,0, moving "left"
  val diagonalDown = matrix.indices.sumOf { index ->
    if (index == 0) return@sumOf 0
    var start = index
    var diagonal = ""
    matrix.indices.forEach { i ->
      runCatching { diagonal += (matrix[start++][i]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }

  // flip the matrix, do the same as above
  val revDiagonalUpper = matrix.reversed().indices.sumOf { index ->
    var start = index
    var diagonal = ""
    matrix.reversed().indices.forEach { i ->
      runCatching { diagonal += (matrix.reversed()[i][start++]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  val revDiagonalDown = matrix.reversed().indices.sumOf { index ->
    if (index == 0) return@sumOf 0
    var start = index
    var diagonal = ""
    matrix.reversed().indices.forEach { i ->
      runCatching { diagonal += (matrix.reversed()[start++][i]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  println(rightLeft + upDown + diagonalUpper + diagonalDown + revDiagonalUpper + revDiagonalDown)
  // 2549 RIGHT ANSWER

  var acc = 0
  matrix.indices.forEach { i ->
    matrix[i].indices.forEach loop@{ j ->
      val message = getCube(matrix, i, j)
      if (message.isBlank()) return@loop
      val one = listOf(message[0], message[4], message[8]).joinToString("")
      val two = listOf(message[2], message[4], message[6]).joinToString("")
      if ((one == "MAS" || one == "SAM") && (two == "MAS" || two == "SAM")) acc++
    }
  }
  println(acc)
}

fun getColumn(matrix: List<List<Char>>, col: Int) = CharArray(matrix.size) { matrix[it][col] }
fun getCube(matrix: List<List<Char>>, x: Int, y: Int): String {
  runCatching {
    return listOf(
      matrix[x][y],
      matrix[x][y+1],
      matrix[x][y+2],
      matrix[x+1][y],
      matrix[x+1][y+1],
      matrix[x+1][y+2],
      matrix[x+2][y],
      matrix[x+2][y+1],
      matrix[x+2][y+2],
    ).toList().joinToString("")
  }
  return ""
}
