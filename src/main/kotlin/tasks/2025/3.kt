package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<List<Int>>, Long>(2025, 3) {
    input { it.map { it.map { c -> c.digitToInt() } } }
    fun parse(digits: List<Int>, k: Int): Long {
        val stack = ArrayDeque<Int>()

        var toRemove = digits.size - k

        for (d in digits) {
            while (toRemove > 0 && stack.isNotEmpty() && stack.last() < d) {
                stack.removeLast()
                toRemove--
            }
            stack.addLast(d)
        }

        return stack.take(k)
            .fold(0L) { acc, digit -> acc * 10 + digit }

    }
    part(1, 357) {
        it.sumOf { bank ->
            parse(bank, 2)
        }
    }

    part(2, 3121910778619L) {
        it.sumOf { bank ->
            parse(bank, 12)
        }
    }

}

fun mainOld() = day<List<String>, Long>(2025, 3) {
    part(1, 357) {
        it.sumOf { bank ->
            val sorted =
                bank.mapIndexed { index, c -> index to c.digitToInt().toLong() }.sortedByDescending { it.second }
            for (first in sorted) {
                for (second in sorted) {
                    if (first == second) continue
                    if (second.first > first.first) {
                        return@sumOf first.second * 10 + second.second
                    }
                }
            }
            0
        }
    }
}