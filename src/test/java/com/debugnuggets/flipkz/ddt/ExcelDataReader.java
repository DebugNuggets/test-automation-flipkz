package com.debugnuggets.flipkz.ddt;

import static com.debugnuggets.flipkz.constants.NameConstants.EXCEL_FILE_PATH;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ExcelDataReader {

    public static void main(String[] args) throws IOException {

        Properties properties = PropertiesUtil.getInstance().getProperties();
        String pathToExcel = System.getProperty("user.dir") + properties.getProperty(EXCEL_FILE_PATH);
        FileInputStream fileInputStream = new FileInputStream(pathToExcel);

        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        int numberOfRow = sheet.getLastRowNum();
        int numberOfCell = sheet.getRow(0).getLastCellNum();

        String[][] arrayExcelData = new String[numberOfCell][numberOfRow];

        for (int row = 0; row < numberOfRow; row++) {
            Row currentRow = sheet.getRow(row);
            int arrayRow = 0;
            for (int cell = 0; cell < numberOfCell; cell++) {
                String value = currentRow.getCell(cell).toString();
                arrayExcelData[arrayRow][row] = value;
                arrayRow++;
            }
        }

//        for (Row myRow : sheet) {
//            for (Cell myCell : myRow) {
//                System.out.println(myCell.toString());
//            }
//        }

        System.out.println(Arrays.deepToString(arrayExcelData).replace("], ", "]\n"));

        fileInputStream.close();
        workbook.close();

    }
}
