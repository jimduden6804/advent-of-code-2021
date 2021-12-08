package day4.part1

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldBeFalse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

const val testyInput = "/src/test/kotlin/day4/part1/testy.txt"

internal class BingoKtTest {
    @Test
    fun `should read drawings`() {
        val input = readValues(testyInput)
        drawings(input) shouldBeEqualTo listOf(
            7,
            4,
            9,
            5,
            11,
            17,
            23,
            2,
            0,
            14,
            21,
            24,
            10,
            16,
            13,
            6,
            15,
            25,
            12,
            22,
            18,
            20,
            8,
            19,
            3,
            26,
            1
        )
    }

    @Test
    fun `should read boards`() {
        val input = readValues(testyInput)
        val expectedBoard = listOf(
            listOf(22, 13, 17, 11, 0),
            listOf(8, 2, 23, 4, 24),
            listOf(21, 9, 14, 16, 7),
            listOf(6, 10, 3, 18, 5),
            listOf(1, 12, 20, 15, 19)
        )
        boards(input).first() shouldBeEqualTo expectedBoard
    }

    @Test
    fun `should recognise winning row`() {
        val row: Row = listOf(1, 2, 3, 4, 5)
        val drawings1 = listOf(1, 2, 3, 4, 5)
        val drawings2 = listOf(1, 2, 4, 5)
        val drawings3 = listOf(1, 2, 4, 5, 3, 7, 9)

        row.rwon(drawings1).shouldBeTrue()
        row.rwon(drawings2).shouldBeFalse()
        row.rwon(drawings3).shouldBeTrue()
    }

    @Test
    fun `should recognise winning board`() {
        val board: Board = listOf(
            listOf(22, 13, 17, 11, 0),
            listOf(8, 2, 23, 4, 24),
            listOf(21, 9, 14, 16, 7),
            listOf(6, 10, 3, 18, 5),
            listOf(1, 12, 20, 15, 19)
        )

        val drawingsRowWin = listOf(21, 9, 14, 16, 7)
        val drawingsColWin = listOf(13, 2, 9, 10, 12)
        val drawingsNoWin = listOf(1, 2, 4, 5, 3, 7, 9)

        board.won(drawingsRowWin).shouldBeTrue()
        board.won(drawingsColWin).shouldBeTrue()
        board.won(drawingsNoWin).shouldBeFalse()
    }

    @Test
    fun `should score board`() {
        val board: Board = listOf(
            listOf(14, 21, 17, 24, 4),
            listOf(10, 16, 15, 9, 19),
            listOf(18, 8, 23, 26, 20),
            listOf(22, 11, 13, 6, 5),
            listOf(2, 0, 12, 3, 7)
        )

        val drawingsRowWin = listOf(
            7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24
        )

        board.score(drawingsRowWin) shouldBeEqualTo 4512
    }
}