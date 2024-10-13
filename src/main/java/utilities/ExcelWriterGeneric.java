package utilities;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriterGeneric {
    private static XSSFCellStyle headerStyle;
    private static int rows;
    private static int columns;

    public static void writeExcelSheet(XSSFWorkbook workbook, Object[][] data, String sheetName) {
        final var sheet = workbook.createSheet(sheetName);

        rows = data.length;
        columns = data[0].length;

        writeHeaders(sheet, data[0]);
        writeContent(sheet, data);
        fixWidth(sheet);
    }

    public static void writeData(XSSFWorkbook workbook, String path) {
        try {
            final var fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException ioException) {
            ioException.fillInStackTrace();
        }
    }

    public static void initHeaderStyle(XSSFWorkbook workbook) {
        headerStyle = workbook.createCellStyle();

        final var font = workbook.createFont();
        font.setBold(true);

        final var color = new XSSFColor(Color.YELLOW, null);

        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(color);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
    }

    private static void writeHeaders(XSSFSheet sheet, Object[] headers) {
        final var headerRow = sheet.createRow(0);
        for (var j = 0; j < columns; j++) {
            final var cell = headerRow.createCell(j);
            cell.setCellValue((String) headers[j]);
            cell.setCellStyle(headerStyle);
        }
    }

    private static void writeContent(XSSFSheet sheet, Object[][] content) {
        for (var i = 1; i < rows; i++) {
            final var newRow = sheet.createRow(i);
            final var dataRow = content[i];

            for (var j = 0; j < columns; j++) {
                final var cell = newRow.createCell(j);
                writeSingleData(dataRow[j], cell);
            }
        }
    }

    private static void fixWidth(XSSFSheet sheet) {
        for (var i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
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
}