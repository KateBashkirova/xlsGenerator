package xlsBuilders;

import customExeptions.ExceedingLineLimitException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static xlsBuilders.styles.CellStyle.getCellStyle;


public class MultipleOrderFileBuilder {
    private static final XSSFWorkbook workbook = new XSSFWorkbook();
    private Sheet sheet;
    private int totalRowsInSheet;
    private int totalCellsInSheet;

    private static final int MAX_ROW_NUMBER = 1048576;
    private static final int MAX_CELL_NUMBER = 16384;
    private static final int MAX_CELL_LENGTH = 32767;

    public XSSFWorkbook buildWorkbook(String sheetName, List<Object> objectList) throws ExceedingLineLimitException {
        createSheet(sheetName, objectList);
        return workbook;
    }

    public void createSheet(String sheetName, List<Object> objectList) throws ExceedingLineLimitException {
        // create list in workbook
        String name = WorkbookUtil.createSafeSheetName(sheetName);
        sheet = workbook.createSheet(name);

        int rowNumber = 1; // потому что нулевая строка занята названиями колонок
        for(Object object : objectList) {
            String classFieldValues = object.toString();
            List<String> readyFields = new ArrayList<>(Arrays.asList(classFieldValues.split(";")));
            fillInfo(readyFields, rowNumber);
            rowNumber++;
        }
    }

    public void fillInfo(List<String> info, int rowNumber) throws ExceedingLineLimitException {
        // завершит ли метод работу выбрасыванием ошибки или всё-таки обернуть в if-else?
        totalRowsInSheet++;
        if(totalRowsInSheet > MAX_ROW_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum row number");
        Row row = sheet.createRow(rowNumber);
        for(int i = 0; i < info.size(); i++) {
            totalCellsInSheet++;
            if(totalCellsInSheet > MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
            row.createCell(i).setCellValue(info.get(i));
        }
        setStyle(row);
        // auto-size columns
        int numOfColumns = sheet.getRow(rowNumber).getLastCellNum(); //вернёт индекс последней ячейки (считает незаполненные)
        // подстраиваем колонки под текст
        for(int j=0; j<numOfColumns; j++) {
            sheet.autoSizeColumn(j);
        }
    }

    public void setStyle(Row row) {
        // применить стили для всех заполненных ячеек в строках
        XSSFCellStyle cellStyle = getCellStyle(workbook);
        for (Cell unstyledCell : row) {
            unstyledCell.setCellStyle(cellStyle);
        }
    }
}
