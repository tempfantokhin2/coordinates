package org.example;

public class FunctionParabola implements IMathFunction {
    private final double a;
    private final double b;
    private final double c;

    public FunctionParabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calculate(double x) {
        return a * x * x + b * x + c;
    }
}
