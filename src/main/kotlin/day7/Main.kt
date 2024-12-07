package day7

import java.io.File
import java.util.LinkedList
import kotlin.math.pow

fun main() {
  val input = File("src/main/kotlin/day7/input.txt")
    .readLines()
  val total = input.sumOf { line ->
    val split = line.split(" ")
    val goal = split.first().replace(":", "").toLong()
    val numbers = split.slice(1..split.lastIndex)
    val spaces = numbers.size - 1
    // coefficient should be 2 for part one, 3 for part two
    val coefficient = 3.0
    val toBinary = (coefficient.pow(spaces.toDouble()) - 1).toInt()
    val combinations = (0..toBinary).map {
      it.toString(coefficient.toInt()).padStart(spaces, '0')
    }
//    println(combinations)
    val res = combinations.map { bits ->
      val linked = LinkedList(numbers)
      bits.forEachIndexed { index, c ->
        linked.add(index * 2 + 1, toOp(c).toString())
      }
      var sum = linked.first().toLong()
      val rest = linked.slice(1 until linked.size)
      rest.windowed(2, 2, false) {
        val operator = Operator.valueOf(it[0])
        val next = it[1].toLong()
        when (operator) {
          Operator.Plus -> sum += next
          Operator.Multiply -> sum *= next
          Operator.Join -> sum = (sum.toString() + next.toString()).toLong()
        }
      }
//      print("$linked -> $sum")
//      println()
      sum
    }
    if (res.contains(goal)) goal else 0
  }
  println(total)
  // 456565678667482 correct for part 2
}

fun toOp(c: Char): Operator {
  return when (c) {
    '0' -> Operator.Plus
    '1' -> Operator.Multiply
    '2' -> Operator.Join
    else -> throw IllegalArgumentException("")
  }
}

enum class Operator {
  Plus, Multiply, Join
}
