package com.ecom.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void loadExcelFile(String filePath, String sheetName) {
        try (FileInputStream file = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        if (cell == null) {
            return "";  // Return empty if cell is null
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";  // Return empty for other types (e.g., BLANK)
        }
    }


    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
