package com.rombsquare.equadratic.gamescreen

import kotlin.ranges.random
import kotlin.to

data class Eq(val a: Int, val b: Int, val c: Int, val x1: Int = 0, val x2: Int = 0) {
    fun format(isClassic: Boolean = true): String {
        if (isClassic) {
            return " ${a}x² + ${b}x + ${c} = 0"
                .replace("+ -", "- ")
                .replace(" 1x", " x")
                .replace("-1x²", "-x²")
                .replace(" + 0x", "")
                .replace(" + 0", "")
                .substring(1)
        }

        return "$a, $b, $c"
    }
}

fun getEqByRoots(x1: Int, x2: Int, a: Int = 1): Eq {
    return Eq(
        a = a,
        b = -(x1 + x2)*a,
        c = x1*x2*a,
        x1 = kotlin.comparisons.minOf(x1, x2),
        x2 = kotlin.comparisons.maxOf(x1, x2)
    )
}


fun getRandomEq(minX: Int, maxX: Int, minA: Int, maxA: Int): Eq {
    val a = (minA..maxA).random()
    return getEqByRoots(
        (minX..maxX).random(),
        (minX..maxX).random(),
        if (a == 0) 1 else a
    )
}

fun getEqByDiff(diff: String): Eq {
    val definitions = mapOf(
        "easy" to listOf(1, 10, 1, 1), // min root, max root, min a, max a
        "medium" to listOf(-10, 10, 1, 1),
        "hard" to listOf(-10, 10, -10, 10)
    )

    val definition = definitions[diff]

    return getRandomEq(
        definition?.get(0) ?: 1,
        definition?.get(1) ?: 1,
        definition?.get(2) ?: 1,
        definition?.get(3) ?: 1,

    )
}

// Growth mode
fun getEqByLevel(lvl: Int): Eq {
    return getRandomEq(
        minX = -3 - lvl*2,
        maxX = 3 + lvl*2,
        minA = -1 - lvl,
        maxA = 1 + lvl
    )
}