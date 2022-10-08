package com.debugnuggets.flipkz.ddt;

import com.debugnuggets.flipkz.util.PropertiesUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static com.debugnuggets.flipkz.constants.NameConstants.EXCEL_FILE_PATH;

public class ExcelWriteData {

    public static void main(String[] args) throws Exception{
        Properties properties = PropertiesUtil.getInstance().getProperties();
        String pathToExcel = System.getProperty("user.dir") + properties.getProperty(EXCEL_FILE_PATH);

        FileInputStream inStream = new FileInputStream(pathToExcel);
        Workbook workbook = WorkbookFactory.create(inStream);
        Sheet worksheet = workbook.getSheetAt(0);

        Cell status = worksheet.getRow(1).getCell(1);
        status.setCellValue("test");

        FileOutputStream outStream = new FileOutputStream(pathToExcel);
        workbook.write(outStream);

        outStream.close();
        workbook.close();
        inStream.close();

    }

}
