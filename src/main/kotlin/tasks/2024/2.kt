package de.joker.tasks.`2024`

import de.joker.utils.day

fun main() = day<List<List<Int>>, Int>(2024, 2) {
    input {
        it.map { row ->
            row.split(" ").map { num -> num.toInt() }
        }
    }

    fun isSafe(levels: List<Int>): Boolean {
        val diffs = levels.zipWithNext { a, b -> b - a }
        val isIncreasing = diffs.all { it > 0 }
        val isDecreasing = diffs.all { it < 0 }
        val withinRange = diffs.all { diff -> diff in -3..-1 || diff in 1..3 }

        return (isIncreasing || isDecreasing) && withinRange
    }

    /*
    The engineers are trying to figure out which reports are safe. The Red-Nosed reactor safety systems can only tolerate levels that are either gradually increasing or gradually decreasing. So, a report only counts as safe if both of the following are true:

The levels are either all increasing or all decreasing.
Any two adjacent levels differ by at least one and at most three.
     */
    part(1, 2) {
        it.count { levels -> isSafe(levels) }
    }

    /*
    The engineers are surprised by the low number of safe reports until they realize they forgot to tell you about the Problem Dampener.

The Problem Dampener is a reactor-mounted module that lets the reactor safety systems tolerate a single bad level in what would otherwise be a safe report. It's like the bad level never happened!

Now, the same rules apply as before, except if removing a single level from an unsafe report would make it safe, the report instead counts as safe.
     */

    part(2, 4) {
        it.count { levels ->
            if (isSafe(levels)) {
                true
            } else {
                levels.indices.any { index ->
                    val modifiedLevels = levels.toMutableList().apply { removeAt(index) }
                    isSafe(modifiedLevels)
                }
            }
        }
    }
}