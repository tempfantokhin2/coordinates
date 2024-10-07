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
        for (int i = 0; i < count; i++) {
            double x = minX + (maxX - minX) * i / (count - 1);
            double y = function.calculate(x);
            points.add(new Point(x, y));
        }
        return points;
    }

    public static void savePointsToExcelFile(List<Point> points, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Points");

            for (int i = 0; i < points.size(); i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(points.get(i).getX());
                row.createCell(1).setCellValue(points.get(i).getY());
            }

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }
    }
}
