package dayTwo

import java.io.File

class Submarine{
    var depth: Int = 0
    var horPos: Int = 0

    fun down(value: Int) {
        this.depth += value
    }
    fun forward(value: Int) {
        this.horPos += value
    }

    fun move(input: Pair<String, Int>){
        when (input.first) {
            "up" -> this.down(-input.second)
            "down" -> this.down(input.second)
            "forward" -> this.forward(input.second)
            else -> println("error")
        }
    }

}


private fun countIncreases(prev: Int, current: Int): Int {
    return if (current > prev) 1 else 0
}

private fun readValues(): List<Pair<String, Int>> {
    return File("/home/jduden/code/learning/advent-of-code-2021/src/main/kotlin/dayTwo/values.txt")
        .readLines()
        .map { it.split(" ").take(2) }
        .map {Pair(it[0], it[1].toInt())}

}


fun main() {
    val submarine = Submarine()
    val values = readValues()
    println(values)
    values.map{
        submarine.move(it)
    }
    println(submarine.horPos * submarine.depth)
}
