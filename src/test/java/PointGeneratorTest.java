import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PointGeneratorTest {

    private List<Point> points;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Настройка тестовых данных
        double minX = 0;
        double maxX = Math.PI;
        int count = 5;

        // Генерация точек
        PointGenerator pointGeneratorTest1 = new PointGenerator(new FunctionSinus(2));
        points = pointGeneratorTest1.generatePoints(minX, maxX, count);

        // Создание временного файла
        tempFile = File.createTempFile("temp_points", ".xlsx");
        tempFile.deleteOnExit();
    }

    @Test
    public void testGeneratePoints_GeneratesCorrectNumberOfPoints() {
        assertEquals(5, points.size(), "Проверка количества точек");
    }

    @Test
    public void testGeneratePoints_GeneratedPointsHaveExpectedValues() {
        for (int i = 0; i < 5; i++) {
            double x = 0 + (Math.PI - 0) * i / (5 - 1);
            double expectedY = Math.sin(x*2);
            int finalI = i;
            int finalI1 = i;
            assertAll(
                    () -> assertEquals(x, points.get(finalI1).getX()),
                    () -> assertEquals(expectedY, points.get(finalI).getY(), 0.001)
            );
        }
    }

    @Test
    public void testSavePointsToExcelFile_SavesPointsToFile() throws IOException {
        // Act
        Main.savePointsToExcelFile(points, tempFile.getAbsolutePath());

        // Assert
        assertTrue(Files.exists(Paths.get(tempFile.getAbsolutePath())));

        // Чтение файла и проверка содержимого
        try (Workbook workbook = new XSSFWorkbook(tempFile.getAbsolutePath())) {
            Sheet sheet = workbook.getSheetAt(0);

            assertEquals(5, sheet.getLastRowNum() + 1); // 6 строк включая заголовок

            for (int i = 0; i < 5; i++) {
                Row row = sheet.getRow(i);
                assertEquals(points.get(i).getX(), row.getCell(0).getNumericCellValue());
                assertEquals(points.get(i).getY(), row.getCell(1).getNumericCellValue());
            }
        } finally {
            Files.deleteIfExists(Paths.get(tempFile.getAbsolutePath()));
        }
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (tempFile != null && tempFile.exists()) {
            Files.deleteIfExists(Paths.get(tempFile.getAbsolutePath()));
        }
    }
}
