package com.xuzimian.java.learning.kotlin.grammar.funtion

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


/**
 * 中缀函数
 */
class InfixFunction {

    @Test
    fun mapToDemo() {
        val numberMap = mapOf(
            1 to "one",
            2 to "two",
            3 to "three"
        )

        assertEquals(
            mapOf(
                1.to("one"),
                2.to("two"),
                3.to("three")
            ), numberMap
        )
    }
}

