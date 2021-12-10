package day7.part2

import java.io.File
import kotlin.math.absoluteValue

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}

fun cost(distance: Int): Int {
   return if (distance == 0) 0 else distance + cost(distance-1)
}

fun positionCost(pos: Int, allPositions: List<Int>): Pair<Int, Int> {
    return pos to allPositions.sumOf { cost((it - pos).absoluteValue) }
}

fun main() {
    val positions = readValues("/src/main/kotlin/day7/part2/values.txt").first().split(",").map { it.toInt() }
    val result = (positions.indices)
        .map { positionCost(it, positions) }
        .minByOrNull { it.second }
    println(result)
}
