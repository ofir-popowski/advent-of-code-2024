package day4

import java.io.File

//fun main() {
//  val input = File("src/main/kotlin/day4/input.txt").readText()
//  val matrix = input.lines().map { line ->
//    line.map { it }
//  }
//  for (i in matrix.indices) {
//    for (j in matrix[i].indices) {
//
//    }
//  }
//}
//
//fun checkRight(matrix: List<List<Char>>, x: Int, y: Int): Boolean {
//  runCatching {
//    val a = matrix[x][y]
//    val b = matrix[x+1][y]
//    val c = matrix[x+2][y]
//    val d = matrix[x+3][y]
//    val result = "$a$b$c$d"
//    if (result == "XMAS" || result == "SAMX") return true
//  }
//  return false
//}
//
//fun checkDown(matrix: List<List<Char>>, x: Int, y: Int): Boolean {
//  runCatching {
//    val a = matrix[x][y]
//    val b = matrix[x][y+1]
//    val c = matrix[x][y+2]
//    val d = matrix[x][y+3]
//    val result = "$a$b$c$d"
//    if (result == "XMAS" || result == "SAMX") return true
//  }
//  return false
//}
//
//fun checkDiag(matrix: List<List<Char>>, x: Int, y: Int): Boolean {
//  runCatching {
//    val a = matrix[x][y]
//    val b = matrix[x+1][y+1]
//    val c = matrix[x+2][y+2]
//    val d = matrix[x+3][y+3]
//    val result = "$a$b$c$d"
//    if (result == "XMAS" || result == "SAMX") return true
//  }
//  return false
//}
//
//fun checkDiagNeg(matrix: List<List<Char>>, x: Int, y: Int): Boolean {
//  runCatching {
//    val a = matrix[x][y]
//    val b = matrix[x-1][y+1]
//    val c = matrix[x-2][y+2]
//    val d = matrix[x-3][y+3]
//    val result = "$a$b$c$d"
//    if (result == "XMAS" || result == "SAMX") return true
//  }
//  return false
//}

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
  val diagonalUpper = matrix.indices.sumOf { index ->
    var start = index
    var diagonal = ""
    matrix.indices.forEach { i ->
      runCatching { diagonal += (matrix[i][start++]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  val diagonalDown = matrix.indices.sumOf { index ->
    if (index == 0) return@sumOf 0
    var start = index
    var diagonal = ""
    matrix.indices.forEach { i ->
      runCatching { diagonal += (matrix[start++][i]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }

  val rev = matrix.reversed().map { it }
  val revDiagonalUpper = rev.indices.sumOf { index ->
    var start = index
    var diagonal = ""
    rev.indices.forEach { i ->
      runCatching { diagonal += (rev[i][start++]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  val revDiagonalDown = rev.indices.sumOf { index ->
    if (index == 0) return@sumOf 0
    var start = index
    var diagonal = ""
    rev.indices.forEach { i ->
      runCatching { diagonal += (rev[start++][i]) }
    }
    diagonal.windowed(4).count { it == "XMAS" || it == "SAMX" }
  }
  println(rightLeft + upDown + diagonalUpper + diagonalDown + revDiagonalUpper + revDiagonalDown)
  // 2549 RIGHT ANSWER
}

fun getColumn(matrix: List<List<Char>>, col: Int) = CharArray(matrix.size) { matrix[it][col] }
