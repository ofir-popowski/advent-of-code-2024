package day1

import java.io.File
import kotlin.math.absoluteValue

fun main() {
  val pairs = File("src/main/kotlin/day1/input.txt")
    .readLines()
    .map { line ->
      line.split("   ").let {
        it.first().toInt() to it.last().toInt()
      }
    }
  val leftList = pairs.map { it.first }.sorted()
  val rightList = pairs.map { it.second }.sorted()
  var acc = 0
  for (i in 0 .. pairs.lastIndex) {
    val left = leftList[i]
    val right = rightList[i]
    acc += (left - right).absoluteValue
  }
  println("part1: distance $acc")

  val similarity = leftList.sumOf { left ->
    val count = rightList.count { right -> left == right }
    left * count
  }
  println("part2: similarity $similarity")
}
