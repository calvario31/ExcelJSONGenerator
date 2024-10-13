package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {
    private static final String excelPath = "src/main/resources/excel/data.xlsx";
    private static final String newExcelPath = "src/main/resources/excel/new.xlsx";

    private static XSSFWorkbook getWorkbook() {
        try {
            return new XSSFWorkbook(excelPath);
        } catch (IOException ioException) {
            Logs.error("Error al leer del excel");
            throw new RuntimeException();
        }
    }

    public static void writeData(String sheetName, String[] headers, Object[][] data) {
        final var workbook = getWorkbook();
        final var sheet = workbook.createSheet(sheetName);
        writeHeaders(sheet, headers);
        writeContent(sheet, data);
        fixWidth(sheet, data[0].length);
        writeWorkbookToExcel(workbook);
    }

    public static void writeWorkbookToExcel(XSSFWorkbook workbook) {
        try {
            final var fileOutputStream = new FileOutputStream(newExcelPath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException ioException) {
            Logs.error("IOException: %s", ioException.getLocalizedMessage());
        }
    }

    private static void writeHeaders(XSSFSheet sheet, String[] headers) {
        final var row = sheet.createRow(0);
        final var n = headers.length;

        for (var i = 0; i < n; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
    }

    private static void writeContent(XSSFSheet sheet, Object[][] content) {
        final var rows = content.length;
        final var columns = content[0].length;

        for (var i = 0; i < rows; i++) {
            final var newRow = sheet.createRow(i + 1);
            final var dataRow = content[i];

            for (var j = 0; j < columns; j++) {
                final var cell = newRow.createCell(j);
                writeSingleData(dataRow[j], cell);
            }
        }
    }

    private static void writeSingleData(Object value, XSSFCell cell) {
        if (value.getClass() == Integer.class) {
            cell.setCellValue((int) value);
        } else if (value.getClass() == String.class) {
            cell.setCellValue((String) value);
        } else if (value.getClass() == Double.class) {
            cell.setCellValue((double) value);
        } else if (value.getClass() == Boolean.class) {
            cell.setCellValue((boolean) value);
        }
    }

    private static void fixWidth(XSSFSheet sheet, int columns) {
        for (var i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
