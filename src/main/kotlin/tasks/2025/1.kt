package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<Pair<Char, Int>>, Int>(2025, 1) {
    input {
        it.map { row ->
            val identifier = row[0]
            val value = row.substring(1).toInt()

            identifier to value
        }
    }


    /*
    The actual password is the number of times the dial is left pointing at 0 after any rotation in the sequence.

    The dial starts by pointing at 50.

    Because the dial is a circle, turning the dial left from 0 one click makes it point at 99. Similarly, turning the dial right from 99 one click makes it point at 0.
     */
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

    /*
    you're actually supposed to count the number of times any click causes the dial to point at 0, regardless of whether it happens during a rotation or at the end of one.
     */
    part(2, 6) {
        var position = 50
        var zeroCount = 0

        it.forEach { (direction, value) ->
            repeat(value) {
                position = when (direction) {
                    'L' -> (position - 1).mod(100)
                    'R' -> (position + 1).mod(100)
                    else -> error("Invalid direction")
                }

                if (position == 0) {
                    zeroCount++
                }
            }
        }
        zeroCount
    }
}