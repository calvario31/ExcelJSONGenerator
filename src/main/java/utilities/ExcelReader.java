package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {
    private static final String excelPath = "src/main/resources/excel/data.xlsx";

    private static XSSFWorkbook getWorkbook() {
        try {
            return new XSSFWorkbook(excelPath);
        } catch (IOException ioException) {
            Logs.error("Error al leer del excel");
            throw new RuntimeException();
        }
    }

    public static Object[][] readData(String sheetName) {
        final var sheet = getWorkbook().getSheet(sheetName);
        final var rows = sheet.getPhysicalNumberOfRows();
        final var columns = sheet.getRow(0).getPhysicalNumberOfCells();

        final var data = new Object[rows - 1][columns];

        for (var i = 1; i < rows; i++) { // 0 es el encabezado
            final var actualRow = sheet.getRow(i); //1, 2, 3, ..., rows

            for (var j = 0; j < columns; j++) {
                final var actualCell = actualRow.getCell(j);
                data[i - 1][j] = getSingleValue(actualCell);
            }
        }

        return data;
    }

    private static Object getSingleValue(XSSFCell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            case STRING -> cell.getStringCellValue();
            default -> "ERROR";
        };
    }
}