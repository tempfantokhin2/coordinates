package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PointGenerator testPointGenerator1 = new PointGenerator(new FunctionLine(0, 10));
        List<Point> generatedPoints1 = testPointGenerator1.generatePoints(0, 100, 10);
        System.out.println(generatedPoints1);
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