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

fun main() = day<List<String>, Long>(2025, 6) {
    part(1, 4277556) { lines ->
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

        val equations = operatorRow.mapIndexed { index, operator ->
            val numbers = numberRows.map { it[index] }
            Equation(numbers, operator)
        }

        equations.sumOf { eq ->
            when (eq.operators) {
                Operator.PLUS -> eq.numbers.sum()
                Operator.MULTIPLY -> eq.numbers.reduce { acc, n -> acc * n }
            }
        }
    }

    part(2, 3263827) { lines ->
        val maxWidth = lines.maxOf { it.length }

        val cols: List<List<Char>> =
            (0 until maxWidth).map { x ->
                lines.map { row ->
                    if (x < row.length) row[x] else ' '
                }
            }

        var grandTotal = 0L

        var currentNumbers = mutableListOf<Long>()
        var currentOperator: Operator? = null

        for (x in cols.lastIndex downTo 0) {
            val col = cols[x]

            val digits = mutableListOf<Char>()
            var columnIsSpace = true

            for (char in col) {
                if (char != ' ') {
                    columnIsSpace = false

                    if (char.isDigit()) {
                        digits += char
                    } else if (char == '+' || char == '*') {
                        currentOperator = Operator.fromChar(char)
                    }
                }
            }

            if (digits.isNotEmpty()) {
                val value = digits.joinToString("").toLong()
                currentNumbers += value
            }

            if (columnIsSpace || x == 0) {
                if (currentNumbers.isNotEmpty() && currentOperator != null) {
                    val blockResult = when (currentOperator!!) {
                        Operator.PLUS -> currentNumbers.sum()
                        Operator.MULTIPLY -> currentNumbers.reduce { acc, n -> acc * n }
                    }
                    grandTotal += blockResult
                }

                currentNumbers = mutableListOf()
                currentOperator = null
            }
        }

        grandTotal
    }

}