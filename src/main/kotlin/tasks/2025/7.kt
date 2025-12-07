package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<String>, Long>(2025, 7) {
    part(1, 21) { grid ->
        var beams = listOf(grid[0].indexOf('S'))

        var count = 0L

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

    part(2, 40) { grid ->
        val h = grid.size
        val w = grid[0].length

        var beams = Array(h) { LongArray(w) }

        beams[0][grid[0].indexOf('S')] = 1L

        for (y in 0 until h) {
            var moved = true
            while (moved) {
                moved = false
                val next = Array(h) { beams[it].copyOf() }

                for (x in 0 until w) {
                    val k = beams[y][x]
                    if (k == 0L) continue

                    if (grid[y][x] == '^') {
                        if (x > 0) next[y][x - 1] += k
                        if (x < w - 1) next[y][x + 1] += k
                        next[y][x] -= k
                        moved = true
                    }
                }

                beams = next
            }

            if (y + 1 < h) {
                val next = Array(h) { LongArray(w) }
                for (x in 0 until w) {
                    next[y + 1][x] = beams[y][x]
                }
                beams = next
            }
        }

        beams[h - 1].sum()
    }
}
