package day3.part1

import java.io.File


private fun readValues(): List<String> {
    return File("/home/jduden/code/learning/advent-of-code-2021/src/main/kotlin/cDayThree/values.txt")
        .readLines()
}

fun List<Int>.sumsToGamma(): Pair<Int, Int> {
    println(this)
    var gamma = 0
    var beta = 0
    for (i in 0 until this.size - 1) {
        if (this[i] >= this[this.size - 1] / 2) {
            gamma = (gamma shl 1) + 1
            beta = beta shl 1
        } else {
            gamma = gamma shl 1
            beta = (beta shl 1) + 1
        }
    }
    return Pair(gamma, beta)
}

fun main() {
    val foo = readValues()
        .map { it + "1" }
        .map { it.toCharArray().map { c -> c.digitToInt() } }
        .reduce { acc, vec -> acc.zip(vec) { x, y -> x + y } }
        .sumsToGamma()

    println("Gamma: ${foo.first}")
    println("Beta: ${foo.second}")
    println("Mult: ${foo.first * foo.second}")
}
