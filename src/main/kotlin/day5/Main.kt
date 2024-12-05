package day5

import java.io.File

fun main() {
  val rules = File("src/main/kotlin/day5/input_rules.txt").readLines()
  val pagesFile = File("src/main/kotlin/day5/input_pages.txt").readLines()
  val rulesPairs = rules.map { line ->
    line.split("|")
      .let { it.first().toInt() to it.last().toInt() }
  }
  val originalPageLists = pagesFile.map { page -> page.split(",").map { it.toInt() } }
  val pageLists = pagesFile.map { page ->
    page.split(",").map { it.toInt() }.toMutableList()
  }
  for (i in 0..10000) {
    pageLists.forEach { pages ->
      rulesPairs.forEach inner@{ rule ->
        val indexOfFirst = pages.indexOf(rule.first)
        val indexOfSecond = pages.indexOf(rule.second)
        if (indexOfFirst == -1 || indexOfSecond == -1) return@inner
        if (indexOfSecond < indexOfFirst) {
          pages[indexOfFirst] = rule.second
          pages[indexOfSecond] = rule.first
        }
      }
    }
  }
  pageLists.forEach { println(it) }
  var acc = 0
  for (i in 0..originalPageLists.lastIndex) {
    val orig = originalPageLists[i]
    val modded = pageLists[i]
    if (orig != modded) {
      // switch orig for part 1, modded for part 2
      acc += modded[(modded.lastIndex / 2)]
    }
  }
  println(acc)
  // 6260
  // part two 5346
}
