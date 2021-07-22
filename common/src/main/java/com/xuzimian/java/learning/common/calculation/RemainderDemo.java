package com.xuzimian.java.learning.common.calculation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RemainderDemo {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 23})
    void should_print_result_when_mod_two(int operand) {
        System.out.println(String.format("%d %% 2 = %d", operand, operand % 2));
    }
}
