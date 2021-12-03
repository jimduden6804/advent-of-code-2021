package dayOne

import java.io.File


private fun countIncreases(prev: Int, current: Int): Int {
    return if (current > prev) 1 else 0
}

private fun readValues(): List<Int>{
    return File("/home/jduden/code/learning/advent-of-code-2021/src/main/kotlin/a/values.txt").readLines().map { it.toInt() }
}


fun main() {
    val values = readValues()
    var acc = 0
    for (i in 1 until values.size) {
        acc += countIncreases(values[i-1], values[i])
    }
    println("Found $acc increases")
}
