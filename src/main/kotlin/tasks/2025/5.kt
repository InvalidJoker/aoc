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

    /*part(2, 14) { // Causes memory issues with large ranges
        val (ranges, _) = it
        ranges.flatMap { it.toList() }.distinct().count().toLong()
    }*/

    part(2, 14) {
        val (ranges, _) = it

        val sorted = ranges.sortedBy { r -> r.first }

        val merged = mutableListOf<LongRange>()
        var current = sorted.first()

        for (range in sorted.drop(1)) {
            if (range.first <= current.last + 1) {
                current = current.first..maxOf(current.last, range.last)
            } else {
                merged += current
                current = range
            }
        }
        merged += current

        merged.sumOf { r -> (r.last - r.first + 1) }
    }
}