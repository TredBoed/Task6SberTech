package com.reflection;

public interface Calculator {
    /**
     * расчет факториала числа
     *@param number
     */
    //@Cache
    @Metric
    int calcFactorial(int number);
}
