package de.joker.tasks.`2025`

import de.joker.utils.day

enum class Operator(val char: Char) {
    PLUS('+'),
    MULTIPLY('*');

    companion object {
        fun fromChar(c: Char): Operator =
            entries.find { it.char == c } ?: error("Unknown operator: $c")
    }
}

data class Equation(val numbers: List<Long>, val operators: Operator)

fun main() = day<List<Equation>, Long>(2025, 6) {
    input { lines ->
        val (opLinesRaw, numLinesRaw) = lines
            .filter { it.isNotBlank() }
            .partition { line -> line.contains('+') || line.contains('*') }

        val numberRows = numLinesRaw.map { row ->
            row.trim()
                .split(Regex("\\s+"))
                .map { it.toLong() }
        }

        val operatorRow = opLinesRaw.map { row ->
            row.toList()
                .filter { it == '+' || it == '*' }
                .map { Operator.fromChar(it) }
        }.first()

        val numCount = numberRows.first().size
        require(numberRows.all { it.size == numCount }) {
            "Not all number rows have the same amount of numbers"
        }

        operatorRow.mapIndexed { index, operator ->
            val numbers = numberRows.map { it[index] }
            Equation(numbers, operator)
        }
    }

    part(1, 4277556) { equations ->
        equations.sumOf { eq ->
            when (eq.operators) {
                Operator.PLUS -> eq.numbers.sum()
                Operator.MULTIPLY -> eq.numbers.reduce { acc, n -> acc * n }
            }
        }
    }
}