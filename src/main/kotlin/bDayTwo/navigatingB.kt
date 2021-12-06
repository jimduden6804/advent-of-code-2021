package bDayTwo

import java.io.File

class SubmarineWithAim {
    var depth: Int = 0
    var horPos: Int = 0
    var aim: Int = 0

    fun down(value: Int) {
        this.aim += value
    }

    fun forward(value: Int) {
        this.horPos += value
        this.depth += value * this.aim
    }

    fun move(input: Pair<String, Int>) {
        when (input.first) {
            "up" -> this.down(-input.second)
            "down" -> this.down(input.second)
            "forward" -> this.forward(input.second)
            else -> println("error")
        }
    }
}

private fun readValues(): List<Pair<String, Int>> {
    return File("/home/jduden/code/learning/advent-of-code-2021/src/main/kotlin/dayTwo/values_aim.txt")
        .readLines()
        .map { it.split(" ").take(2) }
        .map { Pair(it[0], it[1].toInt()) }

}


fun main() {
    val submarine = SubmarineWithAim()
    val values = readValues()
    println(values)
    values.map {
        submarine.move(it)
    }
    println(submarine.horPos * submarine.depth)
}
