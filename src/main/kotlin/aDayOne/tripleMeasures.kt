package aDayOne

import java.io.File


private fun countIncreases(prev: Int, current: Int): Int {
    return if (current > prev) 1 else 0
}

private fun readValues(): List<Int>{
    return File("/home/jduden/code/learning/advent-of-code-2021/src/main/kotlin/dayOne/values.txt").readLines().map { it.toInt() }
}


fun main() {
    val values = readValues()
    var acc = 0
    for (i in 1 until values.size-2) {
        val tripple1 = (i-1..i+1).toList().map { values[it] }.sum()
        val tripple2 = (i..i+2).toList().map { values[it] }.sum()
        acc += countIncreases(tripple1, tripple2)
    }
    println("Found $acc increases")
}
