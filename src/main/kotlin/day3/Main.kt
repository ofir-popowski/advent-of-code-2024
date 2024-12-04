package day3

import java.io.File

fun main() {
  val lines = File("src/main/kotlin/day3/input.txt")
    .readLines()
  val mul = Regex("""mul\((?<a>\d+),(?<b>\d+)\)""")
  val deactivate = Regex("""don't\(\)""")
  val activate = Regex("""do\(\)""")
  val everything = Regex("""(mul\((?<a>\d+),(?<b>\d+)\))|don't\(\)|do\(\)""")
  var sum: Long = 0
  var activated = true
  lines.forEach { line ->
    println("line: $line")
    val output = everything.findAll(line).map { it.value }.toList()
    println("$output")
    output.forEach { it1 ->
      if (it1.matches(deactivate)) activated = false
      if (it1.matches(activate)) activated = true
      if (activated && it1.matches(mul)) {
        val groups = mul.find(it1)!!.groups
        val a = groups["a"]!!.value.toInt()
        val b = groups["b"]!!.value.toInt()
        sum += a * b
      }
    }
  }
  println("Sum: $sum")
  // wrong: 107519039
  // 102467299
}
