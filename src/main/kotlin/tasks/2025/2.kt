package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<LongRange>, Long>(2025, 2) {
    input {
        it.joinToString("").split(",").map { range ->
            val (start, end) = range.split("-").map { it.toLong() }

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

        var invalidIds = 0L

        it.forEach { range ->
            for (id in range) {
                if (isInvalid(id)) {
                    invalidIds += id
                }
            }
        }

        invalidIds
    }

    part(2, 4174379265) {
        var invalidIds = 0L

        it.forEach { range ->
            for (id in range) {
                val idStr = id.toString()
                val length = idStr.length

                var isInvalid = false

                for (subLength in 1..(length / 2)) {
                    if (length % subLength != 0) continue

                    val subStr = idStr.take(subLength)
                    val repetitions = length / subLength

                    if (subStr.repeat(repetitions) == idStr) {
                        isInvalid = true
                        break
                    }
                }

                if (isInvalid) {
                    invalidIds += id
                }
            }
        }

        invalidIds
    }
}