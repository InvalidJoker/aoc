package de.joker.utils

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal
import com.github.ajalt.mordant.rendering.TextColors.*

private val term = Terminal(AnsiLevel.TRUECOLOR)

private fun ok(msg: String) =
    term.println("${brightGreen("OK")}     $msg")

private fun fail(msg: String) =
    term.println("${brightRed("FAIL")}   $msg")

private fun info(msg: String) =
    term.println("${cyan("INFO")}   $msg")

private fun result(msg: String) =
    term.println("${brightMagenta("RESULT")} $msg")

inline fun <reified I, reified O> day(year: Int, day: Int, builder: Day<I, O>.() -> Unit) =
    Day<I, O>(year, day).apply(builder).run()


class Day<I, O> @PublishedApi internal constructor(val year: Int, val day: Int) {

    private var _mapper: ((List<String>) -> I)? = null

    private var _part1: ((I) -> O)? = null
    private var _part1Test: O? = null

    private var _part2: ((I) -> O)? = null
    private var _part2Test: O? = null

    fun part(part: Int, testValue: O, block: (I) -> O) {
        when (part) {
            1 -> {
                if (_part1 != null) error("Part 1 already exists!")
                _part1 = block
                _part1Test = testValue
            }
            2 -> {
                if (_part2 != null) error("Part 2 already exists!")
                _part2 = block
                _part2Test = testValue
            }
            else -> error("Invalid part")
        }
    }

    fun input(block: (List<String>) -> I) {
        _mapper = block
    }

    private fun runPart(part: Int, block: (I) -> O, testInput: I, expected: O, input: I) {
        val computedTest = block(testInput)
        if (computedTest == expected) {
            ok("Part $part test passed")
            val result = block(input)
            result("Part $part: $result")
        } else {
            fail("Part $part test failed: expected $expected but got $computedTest")
        }

        term.println()
    }

    fun run() {
        if (_mapper == null) _mapper = { it as I } // this is really unsafe, but whatever

        info("Day $day ($year)")
        term.println()

        val cl = Thread.currentThread().contextClassLoader

        val inputResName = "$year/${day.toString().padStart(2, '0')}.txt"
        val testResName = "$year/T${day.toString().padStart(2, '0')}.txt"

        val inputUrl = cl.getResource(inputResName)
            ?: error("Resource '$inputResName' not found on classpath")
        val testUrl = cl.getResource(testResName)
            ?: error("Resource '$testResName' not found on classpath")

        val input = _mapper!!(inputUrl.readText().lines())
        val testInput = _mapper!!(testUrl.readText().lines())

        // Part 1
        if (_part1 == null || _part1Test == null) {
            info("Skipping Part 1")
            term.println()
        } else {
            runPart(1, _part1!!, testInput, _part1Test!!, input)
        }

        // Part 2
        if (_part2 == null || _part2Test == null) {
            info("Skipping Part 2")
            term.println()
        } else {
            runPart(2, _part2!!, testInput, _part2Test!!, input)
        }
    }
}
