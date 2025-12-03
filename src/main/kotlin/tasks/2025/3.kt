package de.joker.tasks.`2025`

import de.joker.utils.day

fun main() = day<List<String>, Long>(2025, 3) {
    fun parse(bank: String, k: Int): Long {
        val digits = bank.map { it.digitToInt() }

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