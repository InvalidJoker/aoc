package de.joker.tasks.`2025`

import de.joker.utils.day

/*
The actual password is the number of times the dial is left pointing at 0 after any rotation in the sequence.

The dial starts by pointing at 50.

Because the dial is a circle, turning the dial left from 0 one click makes it point at 99. Similarly, turning the dial right from 99 one click makes it point at 0.
 */

fun main() = day<List<Pair<Char, Int>>, Int>(2025, 1) {
    input {
        it.map {
            val identifier = it[0]
            val value = it.substring(1).toInt()

            identifier to value
        }
    }

    part(1, 3) {
        var position = 50
        var zeroCount = 0

        it.forEach { (direction, value) ->
            position = when (direction) {
                'L' -> (position - value).mod(100)
                'R' -> (position + value).mod(100)
                else -> error("Invalid direction")
            }

            if (position == 0) {
                zeroCount++
            }
        }
        zeroCount
    }
}