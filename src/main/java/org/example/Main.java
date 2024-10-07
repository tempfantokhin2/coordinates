package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PointGenerator testPointGenerator1 = new PointGenerator(new FunctionLine(0, 10));
        List<Point> generatedPoints1 = testPointGenerator1.generatePoints(0, 100, 10);
        System.out.println(generatedPoints1);
    }
}