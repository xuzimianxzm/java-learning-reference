package com.xuzimian.java.learning.kotlin.grammar.funtion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * 柯里化函数
 */
class CurringFunction {

    @Test
    fun curryingSumFunctionDemo() {
        fun sum(x: Int) = { y: Int ->
            { z: Int -> x + y + z }
        }

        Assertions.assertEquals(6, sum(1)(2)(3))
    }

    /**
     * 最后一个参数是函数类型时，可以采用柯里化风格调用
     */
    @Test
    fun curringLikeDemo() {
        fun curringLike(content: String, apply: (String) -> Unit) {
            apply(content)
        }

        curringLike("look like curring style") { content ->
            println(content)
        }
    }
}