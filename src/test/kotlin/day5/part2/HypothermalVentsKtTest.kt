package day5.part2

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldBeFalse
import org.junit.jupiter.api.Test

const val testyInput = "/src/test/kotlin/day5/part2/testy.txt"

internal class HypothermalVentsKtTest {
    @Test
    fun `should confirm diagonal`() {
        val hor1 :Segment = Pair(Pair(0,9), Pair(5,9))
        val hor2 :Segment = Pair(Pair(5,9), Pair(0,9))
        val vert1 :Segment = Pair(Pair(1,1), Pair(1,6))
        val vert2 :Segment = Pair(Pair(1,6), Pair(1,1))
        val diag1 :Segment = Pair(Pair(8,0), Pair(0,8))
        val diag2 :Segment = Pair(Pair(0,8), Pair(8,0))
        val dot :Segment = Pair(Pair(0,8), Pair(0,8))


        hor1.isDiagonal().shouldBeFalse()
        hor2.isDiagonal().shouldBeFalse()
        vert1.isDiagonal().shouldBeFalse()
        vert2.isDiagonal().shouldBeFalse()
        dot.isDiagonal().shouldBeFalse()
        diag1.isDiagonal().shouldBeTrue()
        diag2.isDiagonal().shouldBeTrue()
    }

    @Test
    fun `should create segment`() {
        val inputLine = "0,9 -> 5,9"
        toSegment(inputLine) shouldBeEqualTo Pair(Pair(0,9), Pair(5,9))
    }

    @Test
    fun `should expand segment`() {
        val seg: Segment = Pair(Pair(1, 4), Pair(3, 4))
        seg.expandToLine() shouldBeEqualTo listOf(Pair(1,4), Pair(2,4), Pair(3,4))
    }


    @Test
    fun `should expand segment diagonal`() {
        val seg: Segment = Pair(Pair(1, 1), Pair(3, 3))
        val seg2: Segment = Pair(Pair(7, 9), Pair(9, 7))
        seg.expandToLine() shouldBeEqualTo listOf(Pair(1,1), Pair(2,2), Pair(3,3))
        seg2.expandToLine() shouldBeEqualTo listOf(Pair(7,9), Pair(8,8), Pair(9,7))
    }
}