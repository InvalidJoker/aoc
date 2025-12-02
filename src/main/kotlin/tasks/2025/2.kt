package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<LongRange>, Long>(2025, 2) {
    input {
        it.joinToString("").split(",").map { range ->
            val (start, end) = range.split("-").map { value -> value.toLong() }

            start..end
        }
    }

    part(1, 1227775554) {
        fun isInvalid(id: Long): Boolean {
            val idStr = id.toString()
            val length = idStr.length

            if (length % 2 != 0) return false

            val half = length / 2
            val firstHalf = idStr.take(half)
            val secondHalf = idStr.substring(half)

            return firstHalf == secondHalf
        }

        it.sumOf { range ->
            range.filter { id -> isInvalid(id) }.sum()
        }
    }

    part(2, 4174379265) {
        fun isInvalid(range: LongRange): Boolean {
            var invalid = false
            for (id in range) {
                val idStr = id.toString()
                val length = idStr.length

                for (subLength in 1..(length / 2)) {
                    if (length % subLength != 0) continue

                    val subStr = idStr.take(subLength)
                    val repetitions = length / subLength

                    if (subStr.repeat(repetitions) == idStr) {
                        invalid = true
                        break
                    }
                }
            }
            return invalid
        }

        it.sumOf { range ->
            range.filter { id -> isInvalid(id..id) }.sum()
        }
    }
}