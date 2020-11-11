package mainFunctions;

import customExeptions.ExceedingLineLimitException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class MultipleOrderFileBuilder {
    private static final XSSFWorkbook workbook = new XSSFWorkbook();
    private static List<Object> orderContent;
    private static List<Object> clientInfo;
    private static List<Object> clientAddress;
    private static List<Sheet> sheets;
    private static List<Row> rows;

    private static final int MAX_ROW_NUMBER = 1048576;
    private static final int MAX_CELL_NUMBER = 16384;
    private static final int MAX_CELL_LENGTH = 32767;

//    // создать лист(ы)
//    public static void createSheets(List<String> sheetNames) {
//        for (String name : sheetNames) {
//            // название листа (safeSheetName если вдруг название будет вводить пользователь)
//            String sheetName = WorkbookUtil.createSafeSheetName(name);
//            // поместить созданный лист в файл
//            Sheet sheet = workbook.createSheet(sheetName);
//            sheets.add(sheet);
//        }
//    }

//    // создаём строки на листе
//    public static void createRows(List<String> sheetsInfo, int sheetNumber) throws ExceedingLineLimitException {
//        for (int totalRows = 0; totalRows < MAX_ROW_NUMBER; totalRows++) {
//            Row row = sheets.get(sheetNumber).createRow(totalRows);
//            rows.add(row);
//            if (totalRows == MAX_ROW_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum row number");
//        }
//    }

    public XSSFWorkbook buildWorkbook(String sheetName, List<Object> values) throws ExceedingLineLimitException, InvocationTargetException {
        cSheet(sheetName, values);
        return workbook;
    }

    public void cSheet(String sheetName, List<Object> values) throws ExceedingLineLimitException, InvocationTargetException {
        // create list in workbook
        String name = WorkbookUtil.createSafeSheetName(sheetName);
        Sheet sheet = workbook.createSheet(name);

        // number of rows == amount of objects in list values
        int totalRows = 0;
        int totalCells = 0;
        for(int rows = 0; rows < values.size(); rows++) {
            Row row = sheet.createRow(rows);
            totalRows++;
            // number of cells in row == amount of info in list.get(rows)
            int cell = 0;
            for (Object value : values) {
                // FIXME: пишет хэши(?) значений вместо реальных значений, как это было в контроллере.
                //  В прошлый раз это пофиксилось orderContent.get(0).getProductName());
                row.createCell(cell).setCellValue(String.valueOf(value));
                cell++;
                totalCells++;
                if (cell == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
            }
            if(totalRows == MAX_ROW_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum row number");
            if(totalCells == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
        }

        // auto-size columns
        int numOfColumns = sheet.getRow(0).getLastCellNum(); //вернёт индекс последней ячейки (считает незаполненные)
        // подстраиваем колонки под текст
        for(int i=0; i<numOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }

//    public static void createCell(Row row, int startCell, int totalCellNumber) throws ExceedingLineLimitException {
//        if (totalCellNumber == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
//        for (int i = startCell; i<totalCellNumber; i++) {
//            row.createCell(i);
//        }
//    }


    public MultipleOrderFileBuilder orderContent(List<Object> orderContent) {
        MultipleOrderFileBuilder.orderContent = orderContent;
        return this;
    }

    public MultipleOrderFileBuilder clientInfo(List<Object> clientInfo) {
        MultipleOrderFileBuilder.clientInfo = clientInfo;
        return this;
    }

    public MultipleOrderFileBuilder clientAddress(List<Object> clientAddress) {
        MultipleOrderFileBuilder.clientAddress = clientAddress;
        return this;
    }

}