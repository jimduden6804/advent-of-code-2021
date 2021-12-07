package day3.part2

import java.io.File
import kotlin.math.pow


private fun readValues(): List<String> {
     val path = System.getProperty("user.dir") + "/src/main/kotlin/day3/part2/values.txt"
     return File(path)
         .readLines()
 }

fun filterByLeastCommon(bitArrays: List<List<Int>>, index: Int = 0): List<Int> {
    val result = (bitArrays
        .sortedBy { it[index] }
        .groupBy { it[index] }
        .values
        .minByOrNull { it.size }!!)
    return if (result.size <= 1) result.first() else filterByLeastCommon(result, index + 1)
}

fun filterByMostCommon(bitArrays: List<List<Int>>, index: Int = 0): List<Int> {
    val result = (bitArrays
        .sortedBy { -it[index] }
        .groupBy { it[index] }
        .values
        .maxByOrNull { it.size }!!)
    return if (result.size <= 1) result.first() else filterByMostCommon(result, index + 1)
}

fun List<Int>.toDecimal(): Int {
    val exponents = this.size - 1 downTo 0
    return this.zip(exponents) {x, y -> x * 2.0.pow(y)}.sum().toInt()
}

fun main() {
     val bitArrays = readValues()
         .map { it.toCharArray().map { c -> c.digitToInt() } }
    val oxygen = filterByMostCommon(bitArrays).toDecimal()
    val carbon = filterByLeastCommon(bitArrays).toDecimal()
    println("Oxygen: $oxygen")
     println("CO2: $carbon")
     println("Mult: ${carbon * oxygen}")
 }
