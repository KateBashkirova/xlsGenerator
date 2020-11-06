package mainFunctions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileBuilder {
    private static final XSSFWorkbook workbook = new XSSFWorkbook();
    private static String[] sheetNames;
    private static int totalRows;

    private static final int MAX_ROW_NUMBER = 1048576;
    private static final int MAX_CELL_NUMBER = 16384;
    private static final int MAX_CELL_LENGTH = 32767;


    public static void build() {
        createSheet();
    }

    // создать лист
    public static void createSheet() {
        for (String name : sheetNames) {
            // название листа (safeSheetName если вдруг название будет вводить пользователь)
            String sheetName = WorkbookUtil.createSafeSheetName(name);
            // лист
            Sheet sheet = workbook.createSheet(sheetName);
        }
    }

    // создаём строки на выбранном листе
    public static void createRow(Sheet sheet, int startRow, int totalRowNumber) {
        if (totalRowNumber == MAX_ROW_NUMBER) // todo: do something
        for (int i = startRow; i<totalRowNumber; i++) {
            Row row = sheet.createRow(i);
        }
    }

    // создаём столбцы в выбранных строках
    public static void createCell(Row row, int startCell, int totalCellNumber) {
        if (totalCellNumber == MAX_CELL_NUMBER); // todo: do something again
        for (int i = startCell; i<totalCellNumber; i++) {
            row.createCell(i);
        }
    }

    // fixme: что будет, если передать сразу массив или список с кучей значений?
    // заполнить ячейки строковыми данными
    public static void fillStringCell(Cell cell, String value) {
        if (value.length() == MAX_CELL_LENGTH) // todo: take a guess
        cell.setCellValue(value);
    }

    // заполняем строковыми данными всю строку сразу
    public static void fillRowWithString(Row row, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].length() == MAX_CELL_LENGTH); // todo: take a guess
            row.createCell(i).setCellValue(values[i]);
        }
    }

    // сеттеры
    public FileBuilder setSheetNames(String[] sheetNames) {
        FileBuilder.sheetNames = sheetNames;
        return this;
    }
    public static void setTotalRows(int totalRows) {
        FileBuilder.totalRows = totalRows;
    }
}
