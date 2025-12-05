package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<Pair<List<LongRange>, List<Long>>, Long>(2025, 5) {
    input {
        val ranges = mutableListOf<LongRange>()
        val ids = mutableListOf<Long>()

        it.forEach { line ->
            if (line.contains("-")) {
                val (start, end) = line.split("-").map { it.toLong() }
                ranges.add(start..end)
            } else if (line.isNotBlank()) {
                ids.add(line.toLong())
            }
        }

        ranges to ids
    }
    part(1, 3) {
        val (ranges, ids) = it
        ids.count { id -> ranges.any { range -> id in range } }.toLong()
    }
}