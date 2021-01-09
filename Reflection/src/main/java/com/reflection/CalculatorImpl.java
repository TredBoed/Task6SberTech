package com.reflection;

public class CalculatorImpl implements Calculator {
    //for ex3
    private int num;
    private int nom;
    private int nam;

    //for ex4
    public static final String MONDAY = "MONDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "";

    //for ex3
    public int getNum() {
        return num;
    }

    public int getNom() {
        return nom;
    }

    public int getNam() {
        return nam;
    }

    public void setNum(int num) {
        this.num = num;
    }

    //for ex2
    @Override
    public int calcFactorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return calcFactorial(number - 1) * number;
        }
    }

    private int calcSomethingAsPrivate() {
        return calcFactorial(this.num);
    }
}

