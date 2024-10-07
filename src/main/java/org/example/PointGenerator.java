package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PointGenerator {
    private IMathFunction function = null;

    public PointGenerator(IMathFunction function) {
        this.function = function;
    }

    public List<Point> generatePoints(double minX, double maxX, int count) {
        List<Point> points = new ArrayList<>();
        double xRandom = minX + (maxX - minX);
        for (int i = 0; i < count; i++) {
            double x = xRandom * i / (count - 1);
            double y = function.calculate(x);
            points.add(new Point(x, y));
        }
        return points;
    }

}
