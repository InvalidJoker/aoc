package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<String>, Int>(2025, 7) {
    part(1, 21) {

        val grid = it
        var beams = listOf(grid[0].indexOf('S'))

        var count = 0

        for (y in 1 until grid.size) {
            val newBeams = mutableListOf<Int>()

            for (x in beams) {
                when (grid[y][x]) {
                    '.' -> {
                        newBeams.add(x)
                    }
                    '^' -> {
                        if (x > 0) newBeams.add(x - 1)
                        if (x < grid[y].length - 1) newBeams.add(x + 1)

                        count++
                    }
                }
            }

            beams = newBeams.toSet().sorted()
        }

        count
    }
}
