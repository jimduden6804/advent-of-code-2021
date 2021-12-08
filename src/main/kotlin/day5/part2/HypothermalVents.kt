package day5.part2

import kotlinx.coroutines.yield
import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

typealias Coordinate = Pair<Int, Int>
typealias Segment = Pair<Coordinate, Coordinate>

fun Segment.x1() = this.first.first
fun Segment.y1() = this.first.second
fun Segment.x2() = this.second.first
fun Segment.y2() = this.second.second
typealias Line = List<Coordinate>

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}

fun toCoordinate(input: String): Coordinate {
    val (x, y) = input.split(",").map { it.toInt() }
    return Pair(x, y)
}

fun toSegment(line: String): Segment {
    val (c1, c2) = line.split(" -> ")
        .map { toCoordinate(it) }
//        .sortedBy { it.second }
//        .sortedBy { it.first }
    return Pair(c1, c2)
}

fun range(start: Int, end: Int): List<Int> {
    val upRange = (start..end).toList()
    return upRange.ifEmpty { start.downTo(end).toList() }
}

fun Segment.isDiagonal(): Boolean = (this.x1() != this.x2()) and (this.y1() != this.y2())


fun Segment.expandToLine(): Line {
    return if (!this.isDiagonal()) {
        val acc = mutableListOf<Coordinate>()
        for (x in range(this.x1(), this.x2())) {
            for (y in range(this.y1(), this.y2())) {
                acc.add(Pair(x, y))
            }
        }
        acc
    } else {
        range(this.x1(), this.x2()).zip(range(this.y1(), this.y2()))
    }
}

fun main() {
    val input = readValues("/src/main/kotlin/day5/part1/values.txt")
    val foo = input.map { toSegment(it) }
        .flatMap { it.expandToLine() }
        .groupBy { it }
        .filter { it.value.size >= 2 }
        .size

    println(foo)
}
