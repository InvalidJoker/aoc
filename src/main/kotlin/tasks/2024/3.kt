package de.joker.tasks.`2024`

import de.joker.utils.day

private sealed interface Instruction {
    val index: Int

    data class Mul(override val index: Int, val a: Int, val b: Int) : Instruction
    data class Do(override val index: Int) : Instruction
    data class Dont(override val index: Int) : Instruction
}

private val mulRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
private val doRegex = Regex("""do\(\)""")
private val dontRegex = Regex("""don't\(\)""")

fun main() = day<String, Int>(2024, 3) {
    input {
        it.joinToString("")
    }

    fun solve(input: String, operators: Boolean): Int {
        val events = mutableListOf<Instruction>()

        mulRegex.findAll(input).forEach { m ->
            val (a, b) = m.destructured
            events += Instruction.Mul(m.range.first, a.toInt(), b.toInt())
        }

        if (operators) {
            doRegex.findAll(input).forEach { m ->
                events += Instruction.Do(m.range.first)
            }
            dontRegex.findAll(input).forEach { m ->
                events += Instruction.Dont(m.range.first)
            }
        }

        events.sortBy { it.index }

        var enabled = true
        var sum = 0

        for (ev in events) {
            when (ev) {
                is Instruction.Do -> enabled = true
                is Instruction.Dont -> enabled = false
                is Instruction.Mul -> if (enabled) sum += ev.a * ev.b
            }
        }

        return sum
    }

    part(1, 161) {
        solve(it, false)
    }

    part(2, 48) {
        solve(it, true)
    }
}
