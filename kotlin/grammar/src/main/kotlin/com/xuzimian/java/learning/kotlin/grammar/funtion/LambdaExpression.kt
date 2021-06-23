package com.xuzimian.java.learning.kotlin.grammar.funtion

import org.junit.jupiter.api.Test

class LambdaExpression {

    @Test
    fun lambdaExpressionDemo() {
        val lambdaExpression = { int: Int -> println(int) }

        lambdaExpression(0)
    }

    @Test
    fun lambdaExpressionFunctionBodyDemo() {
        fun lambdaExpressionFunctionBody(int: Int) = { println(int) }

        lambdaExpressionFunctionBody(1)
        lambdaExpressionFunctionBody(2).invoke()
        lambdaExpressionFunctionBody(3)()
    }

    @Test
    fun lambdaExpressionFunctionBodyDerive1Demo() {
        fun lambdaExpressionFunctionBodyDerive1(int: Int) = { -> println(int) }

        lambdaExpressionFunctionBodyDerive1(4)
        lambdaExpressionFunctionBodyDerive1(5).invoke()
    }

    @Test
    fun lambdaExpressionFunctionBodyDerive2Demo() {
        fun lambdaExpressionFunctionBodyDerive2(int: Int): () -> Unit {
            return { println(int) }
        }

        lambdaExpressionFunctionBodyDerive2(6)
        lambdaExpressionFunctionBodyDerive2(7).invoke()
        lambdaExpressionFunctionBodyDerive2(8)();
    }

    @Test
    fun selfRunningLambdaDemo() {
        { string: String -> println(string) }("自运行的lambda语法")
    }
}