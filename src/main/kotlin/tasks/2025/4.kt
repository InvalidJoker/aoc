package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<String>, Long>(2025, 4) {
    part(1, 13) {
        it.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, c ->
                if (c == '@') {
                    val adjacent = (-1..1).flatMap { dy ->
                        (-1..1).map { dx ->
                            if (dx == 0 && dy == 0) null else Pair(x + dx, y + dy)
                        }
                    }.filterNotNull()

                    val count = adjacent.count { (ax, ay) ->
                        ay in it.indices && ax in it[ay].indices && it[ay][ax] == '@'
                    }

                    if (count < 4) 1L else null
                } else null
            }
        }.sum()
    }

    part(2, 43) {
        val grid = it.map { row -> row.toCharArray() }.toMutableList()

        var totalRemoved = 0L

        while (true) {
            val toRemove = mutableListOf<Pair<Int, Int>>()

            for (y in grid.indices) {
                for (x in grid[y].indices) {
                    if (grid[y][x] == '@') {
                        val adjacent = (-1..1).flatMap { dy ->
                            (-1..1).map { dx ->
                                if (dx == 0 && dy == 0) null else Pair(x + dx, y + dy)
                            }
                        }.filterNotNull()

                        val count = adjacent.count { (ax, ay) ->
                            ay in grid.indices && ax in grid[ay].indices && grid[ay][ax] == '@'
                        }

                        if (count < 4) {
                            toRemove.add(Pair(x, y))
                        }
                    }
                }
            }

            if (toRemove.isEmpty()) break

            for ((x, y) in toRemove) {
                grid[y][x] = '.'
            }

            totalRemoved += toRemove.size
        }

        totalRemoved
    }

}