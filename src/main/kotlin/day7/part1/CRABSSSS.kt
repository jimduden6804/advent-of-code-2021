package day7.part1

import java.io.File
import kotlin.math.absoluteValue

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}

fun List<Int>.median(): Int {
    val size = this.size
    return if (size % 2 == 0) {
        (this[size/2] + this[size/2-1]) / 2
    } else {
        this[(size-1)/2]
    }

}

fun main() {
    val positions = readValues("/src/main/kotlin/day7/part1/values.txt").first().split(",").map { it.toInt() }
    val bestPosition = positions.sorted().median()
    val result = positions.sumOf { (it - bestPosition).absoluteValue }
    println(result)
}
