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

}