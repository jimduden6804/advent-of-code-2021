package day4.part2

import java.io.File

typealias Board = List<List<Int>>
typealias Row = List<Int>

const val BINGO_WIDTH = 5
const val BINGO_HEIGHT = 5

fun readValues(relPath: String): List<String> {
    val path = System.getProperty("user.dir") + relPath
    return File(path)
        .readLines()
}

fun drawings(input: List<String>): List<Int> {
    return input.first().split(",").map { it.toInt() }
}

fun row(rawRow: String): List<Int> =
    rawRow.split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

fun rows(rawRows: List<String>): Board =
    rawRows.map { row(it) }

fun boards(input: List<String>): List<Board> =
    input.drop(2)
        .filter { it.isNotBlank() }
        .chunked(BINGO_HEIGHT)
        .map { rows(it) }

fun Board.transpose(): Board {
    val transpose = Array(BINGO_HEIGHT) { IntArray(BINGO_WIDTH) }
    for (i in 0 until BINGO_WIDTH) {
        for (j in 0 until BINGO_HEIGHT) {
            transpose[j][i] = this[i][j]
        }
    }
    return transpose.toList().map { it.toList() }
}

//Row.won and Board.won would have the same Java Signature.... -> rwon
fun Row.rwon(currentDrawings: List<Int>): Boolean = this.intersect(currentDrawings.toSet()).size >= 5

fun Board.won(currentDrawings: List<Int>): Boolean {
    return this.any { it.rwon(currentDrawings) } or this.transpose().any { it.rwon(currentDrawings) }
}

fun Board.score(currentDrawings: List<Int>): Int {
    return this.map { it.subtract(currentDrawings.toSet()) }
        .map { it.sum() }
        .sum()
        .times(currentDrawings.last())
}

fun game(boards: List<Board>, futureDrawings: List<Int>, round: Int = 1): Int {
    val currentDrawings = futureDrawings.take(round)
    return if (
        ((boards.size <= 1) and (boards.first().won(currentDrawings)))
        or (round > futureDrawings.size)
    ) {
        println("Found ${boards.size} Looser !")
        println(boards.first())
        println(currentDrawings)
        boards.first().score(currentDrawings)
    } else {
        val loosingBoards = boards.filter { it.won(currentDrawings).not() }
        game(loosingBoards, futureDrawings, round + 1)
    }
}

fun main() {
    val input = readValues("/src/main/kotlin/day4/part1/values.txt")
    println("Score: ${game(boards(input), drawings(input), 14)}")
}
