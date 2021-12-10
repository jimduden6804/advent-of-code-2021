package day6.part1

import java.io.File

typealias School = List<LanternFish>

class LanternFish(var age: Int) {

    fun tick(): List<LanternFish> {
        return if (age == 0) {
            this.age = 6
            listOf(this, LanternFish(8))
        } else {
            this.age = this.age - 1
            listOf(this)
        }
    }
}

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}


fun main() {
    val initialAges = readValues("/src/main/kotlin/day6/part1/values.txt").first().split(",").map { it.toInt() }
    var school: School = initialAges.map { LanternFish(it) }

    for (i in 1..80) {
        println(school.map { it.age })
        school = school.flatMap { it.tick() }
    }
    println(school.size)
}
