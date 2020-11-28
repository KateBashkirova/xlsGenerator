package xlsBuilders;

import customExeptions.ExceedingLineLimitException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MultipleOrderFileBuilder {
    private static final XSSFWorkbook workbook = new XSSFWorkbook();
    private static List<Object> orderContent;
    private static List<Object> clientInfo;
    private static List<Object> clientAddress;
    private Sheet sheet;
    private static List<Row> rows;

    private static final int MAX_ROW_NUMBER = 1048576;
    private static final int MAX_CELL_NUMBER = 16384;
    private static final int MAX_CELL_LENGTH = 32767;

    public XSSFWorkbook buildWorkbook(String sheetName, List<Object> values) throws ExceedingLineLimitException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        cSheet(sheetName, values);
        return workbook;
    }

    //FIXME: можно сделать по 3 метода на каждый объект, но в этом нет никакого смысла. Один метод для обработки всех классов разом?
    //TODO: посмотри reflections
    public void cSheet(String sheetName, List<Object> objectList) throws ExceedingLineLimitException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        // create list in workbook
        String name = WorkbookUtil.createSafeSheetName(sheetName);
        sheet = workbook.createSheet(name);

        int rowNumber = 1; // потому что нулевая строка занята названиями колонок
        for(Object object : objectList) {
            String classFieldValues = object.toString();
            List<String> readyFields = new ArrayList<>(Arrays.asList(classFieldValues.split(";")));
            fillInfo(readyFields, rowNumber);
            rowNumber++;
//            for (String str : readyFields) {
//                System.out.println(str);
//            }
//        for (Object object : objectList) {
//            // понять, что за класс у объекта
//            Class<?> cls = object.getClass();
//            // достать все поля этого класса
//            Field[] fields = cls.getFields();
//
//            //private ......OrderContent.productID
////            Field f = cls.getDeclaredField("productID");
//            List<String[]> values = new ArrayList<>();
//            int fieldNumber = 0;
//            for (Field field : fields) {
//                fieldNumber++;
//                cls.
//                // нужен instance???
////                values.add((String[]) field.get(object));
//            }
        }

        // number of rows == amount of objects in list objectList
//        int totalRows = 0;
//        int totalCells = 0;
//        for (Object object : objectList) {
//            Row row = sheet.createRow(totalRows);
//            totalRows++;
//        }
//        for(int rows = 0; rows < objectList.size(); rows++) {
//            Row row = sheet.createRow(rows);
//            totalRows++;
//            // number of cells in row == amount of info in list.get(rows)
//            int cell = 0;
//            for (Object object : objectList) {
//                // FIXME: пишет хэши(?) значений вместо реальных значений, как это было в контроллере.
//                //  В прошлый раз это пофиксилось orderContent.get(0).getProductName());
//                row.createCell(cell).setCellValue(String.valueOf(object));
//                cell++;
//                totalCells++;
//                if (cell == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
//            }
//            if(totalRows == MAX_ROW_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum row number");
//            if(totalCells == MAX_CELL_NUMBER) throw new ExceedingLineLimitException("Exceeded maximum cell number");
//        }

        // auto-size columns
        int numOfColumns = sheet.getRow(0).getLastCellNum(); //вернёт индекс последней ячейки (считает незаполненные)
        // подстраиваем колонки под текст
        for(int i=0; i<numOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void fillInfo(List<String> info, int rowNumber) {
        Row row = sheet.createRow(rowNumber);
        for(int i = 0; i < info.size(); i++) {
            row.createCell(i).setCellValue(info.get(i));
        }

    }

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
