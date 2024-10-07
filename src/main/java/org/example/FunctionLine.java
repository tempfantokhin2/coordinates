package org.example;

public class FunctionLine implements IMathFunction {
    private final double a;
    private final double b;

    public FunctionLine(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calculate(double x) {
        return a * x + b;
    }
}
