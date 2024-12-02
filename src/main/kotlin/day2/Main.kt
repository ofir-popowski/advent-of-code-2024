package day2

import java.io.File
import kotlin.math.absoluteValue

fun main() {
  val numbers = File("src/main/kotlin/day2/input.txt")
    .readLines()
    .map { line -> line.split(" ").map { it.toInt() } }
  val firstPart = numbers.count { (ordered(it) && diffed(it)) }
  println(firstPart)

  val secondPart = numbers.count { (ordered(it) && diffed(it)) || takeOne(it) }
  println(secondPart)
}

fun ordered(nums: List<Int>): Boolean {
  val asc = nums.sorted()
  val desc = nums.sortedDescending()
  return nums == asc || nums == desc
}

fun diffed(nums: List<Int>): Boolean {
  return nums.zipWithNext { a, b -> (a - b).absoluteValue in 1..3 }.all { it }
}

fun takeOne(nums: List<Int>): Boolean {
  // if list isn't ordered and diffed, remove a number and try again
  // either we reached the end and still doesn't work -> return false, or it worked
  for (i in nums.indices) {
    val mutableList = nums.toMutableList()
    mutableList.removeAt(i)
    if (ordered(mutableList) && diffed(mutableList)) return true
  }
  return false
}
