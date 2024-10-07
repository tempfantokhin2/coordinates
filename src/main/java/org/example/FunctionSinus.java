package org.example;

public class FunctionSinus implements IMathFunction {
    private final double a;

    public FunctionSinus(double a) {
        this.a = a;
    }

    @Override
    public double calculate(double x) {
        return Math.sin(a * x);
    }
}

