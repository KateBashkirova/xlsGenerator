package mainFunctions;

import customExeptions.ExceedingLineLimitException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileBuilder {
    private static final XSSFWorkbook workbook = new XSSFWorkbook();
    private static String[] sheetNames;
    private static String[] headlines;
    private static ArrayList<String> rowsInfo;
//    private static String sheetName;
//    private static int totalRows;

    private static final int MAX_ROW_NUMBER = 1048576;
    private static final int MAX_CELL_NUMBER = 16384;
    private static final int MAX_CELL_LENGTH = 32767;


    public static ByteArrayOutputStream build() throws ExceedingLineLimitException, IOException {
//        HashMap<Integer,String> sheetsNameAndNumbers = createSheet();
        ArrayList<Sheet> sheets = createSheet(); // создаём листы
        ArrayList<Row> rows = createRow(sheets.get(0), 0, headlines.length); // создаём заголовки на листе
        createCell(rows.get(0), 0, headlines.length); // в данном случае кол-во ячеек в строке = кол-ву заголовков
        fillRowWithString(rows.get(0), rowsInfo); // заполняем ячейки в строке информацией

        for (Sheet sheet : sheets) {
            int numOfColumns = sheet.getRow(0).getLastCellNum(); // вернёт индекс последней ячейки (считает незаполненные)
            // подстраиваем колонки под текст
            for(int i=0; i<numOfColumns; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        // записываем созданный в памяти Excel документ в файл
        try (FileOutputStream out = new FileOutputStream(new File("D:\\File.xlsx"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        return bos;
    }

    // создать лист(ы)
    public static ArrayList<Sheet> createSheet() {
        ArrayList<Sheet> sheets = null;
        for (String name : sheetNames) {
            // название листа (safeSheetName если вдруг название будет вводить пользователь)
            String sheetName = WorkbookUtil.createSafeSheetName(name);
            // лист
            Sheet sheet = workbook.createSheet(sheetName);
            sheets.add(sheet);
        }
        return sheets;
    }

    // создаём строки на листе
    public static ArrayList<Row> createRow(Sheet sheet, int startRow, int totalRows) throws ExceedingLineLimitException {
        if (totalRows == MAX_ROW_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum row number");
        ArrayList<Row> rows = null;
        for (int i = startRow; i < totalRows; i++) {
            Row row = sheet.createRow(i);
            rows.add(row);
        }
        return rows;
    }

    // создаём столбцы в выбранных строках
    public static void createCell(Row row, int startCell, int totalCellNumber) throws ExceedingLineLimitException {
        if (totalCellNumber == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
        for (int i = startCell; i<totalCellNumber; i++) {
            row.createCell(i);
        }
    }

    // заполняем строковыми данными всю строку сразу
    public static void fillRowWithString(Row row, ArrayList<String> values) throws ExceedingLineLimitException {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).length() == MAX_CELL_LENGTH) throw new ExceedingLineLimitException("Exceeded maximum cell length");
            row.createCell(i).setCellValue(values.get(i));
        }
    }

    // fixme: что будет, если передать сразу массив или список с кучей значений?
    // заполнить ячейки строковыми данными
    public static void fillStringCell(Cell cell, String value) {
        if (value.length() == MAX_CELL_LENGTH) // todo: take a guess
        cell.setCellValue(value);
    }

    // сеттеры
    public FileBuilder setSheetNames(String[] sheetNames) {
        FileBuilder.sheetNames = sheetNames;
        return this;
    }
    public FileBuilder setHeadlines(String[] headlines) {
        FileBuilder.headlines = headlines;
        return this;
    }

    public FileBuilder setRowsInfo(ArrayList<String> rowsInfo) {
        FileBuilder.rowsInfo = rowsInfo;
        return this;
    }

    // оно тебе надо?
    // создать сразу несколько листов
//    public static HashMap<Integer,String> createSheet() {
//        HashMap<Integer,String> sheetsNameAndNumbers = new HashMap<>();
//        int sheetsCounter = 0;
//        for (String name : sheetNames) {
//            // название листа (safeSheetName если вдруг название будет вводить пользователь)
//            String sheetName = WorkbookUtil.createSafeSheetName(name);
//            // лист
//            Sheet sheet = workbook.createSheet(sheetName);
//            sheetsNameAndNumbers.put(sheetsCounter, sheetName);
//            sheetsCounter++;
//        }
//        return sheetsNameAndNumbers;
//    }
}
