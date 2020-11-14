package xlsBuilders.styles;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyle {
    public static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont fontStyle = createCellFont(workbook);
        cellStyle.setFont(fontStyle);
        // добавить границы ячеек
        createBorderStyle(cellStyle);
        return cellStyle;
    }

    public static void createBorderStyle(XSSFCellStyle cellStyle) {
        // границы ячеек
        BorderStyle borderStyleMedium = BorderStyle.MEDIUM;
        cellStyle.setBorderTop(borderStyleMedium);
        cellStyle.setBorderBottom(borderStyleMedium);
        cellStyle.setBorderLeft(borderStyleMedium);
        cellStyle.setBorderRight(borderStyleMedium);

        // цвет границы ячеек
        Colors cellColor = Colors.BLACK;
        cellStyle.setTopBorderColor(cellColor.getColor());
        cellStyle.setBottomBorderColor(cellColor.getColor());
        cellStyle.setLeftBorderColor(cellColor.getColor());
        cellStyle.setRightBorderColor(cellColor.getColor());
    }

    public static XSSFFont createCellFont(XSSFWorkbook workbook) {
        XSSFFont cellFont = workbook.createFont();
        cellFont.setBold(true);
        cellFont.setFontName("Times New Roman");
        return cellFont;
    }
}
