package day6.part2

import java.io.File


class School(val initialAges: List<Int>) {
    var ageToAmount = (0..8).map { it to 0L }.toMap()

    init {
        this.ageToAmount = ageToAmount +
                    initialAges.groupBy { it }
                        .map { (k, v) -> k to v.size.toLong() }
                        .toMap()
//        println(this.ageToAmount)
    }

    fun tick(tickNumber: Int) {
        var ageToAmountChange: MutableMap<Int, Long> = (0..8).map { it to 0L }.toMap().toMutableMap()
        for ((age, amount) in ageToAmount) {
            when (age) {
                0 -> {ageToAmountChange[6] = amount
                    ageToAmountChange[8] = amount
                }
                else -> ageToAmountChange[age-1] = amount + ageToAmountChange[age-1]!!
            }
        }
        ageToAmount = ageToAmountChange
//        println(this.ageToAmount)
        println(tickNumber)
    }

}

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}


fun main() {
    val initialAges = readValues("/src/main/kotlin/day6/part1/values.txt").first().split(",").map { it.toInt() }

    val school = School(initialAges)
    for (i in 1..256) {
        school.tick(i)
    }
    print(school.ageToAmount.values.sum())

}

