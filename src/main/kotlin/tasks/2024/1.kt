package de.joker.tasks.`2024`

import de.joker.utils.day
import kotlin.math.max
import kotlin.math.min

fun main() = day<Pair<List<Int>, List<Int>>, Int>(2024, 1) {
    input {
        val listOne = mutableListOf<Int>()
        val listTwo = mutableListOf<Int>()

        it.forEach {
            val split = it.split("   ")
            listOne += split[0].toInt()
            listTwo += split[1].toInt()
        }

        listOne to listTwo
    }

    part(1, 11) {
        it.first.sorted().zip(it.second.sorted()).fold(0) { acc, i -> acc + (max(i.first, i.second) - min(i.first, i.second)) }
    }

    part(2, 31) {
        it.first.fold(0) { acc, i -> acc + i * it.second.count { it == i } }
    }
}