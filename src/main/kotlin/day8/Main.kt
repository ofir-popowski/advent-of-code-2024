package day8

import java.io.File
import kotlin.math.absoluteValue

fun main() {
  val input = File("src/main/kotlin/day8/example.txt")
  val pos = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
  input.readLines()
    .forEachIndexed { y, line ->
      line.forEachIndexed { x, char ->
        if (char != '.')
          pos.merge(char, mutableListOf(Pair(x, y))) { a, b -> (a + b).toMutableList() }
      }
      println()
    }
  val mutSet = mutableSetOf<Pair<Int, Int>>()
  pos.map { (char, pairs) ->
    println("$char: $pairs")
    for (i in 0..pairs.lastIndex) {
      for (j in i+1 .. pairs.lastIndex) {
        print("[$i,$j]")
        val a = pairs[i]
        val b = pairs[j]
        val newPosX = (a.first - b.first)
        val newPosY = (a.second - b.second)
        val newPosX2 = (b.first - a.first)
        val newPosY2 = (b.second - a.second)
        mutSet.add((a.first + newPosX) to (a.second + newPosY))
        mutSet.add((b.first + newPosX2) to (b.second + newPosY2))
      }
    }
    println()
  }
  val filtered = mutSet
    .filterNot { it.first < 0 }
    .filterNot { it.second < 0 }
    .filterNot { it.first > input.readLines().lastIndex }
    .filterNot { it.second > input.readLines().lastIndex }
  println(filtered)
  println(filtered.size)
  // 392 this was right for part one
}
