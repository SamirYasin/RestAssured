package apache_poi_excel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingFromTGExcelFile {
    static Logger logger = LogManager.getLogger(ReadingFromTGExcelFile.class);

    public static void main(String[] args) throws IOException {
        String excelFilePath = "test_data/TGTestData.xlsx";

        FileInputStream fileInputStream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRow = sheet.getLastRowNum();

        int lastCell = sheet.getRow(1).getLastCellNum();


//        for (int i = 1; i <= lastRow ; i++) {
//            XSSFRow row = sheet.getRow(i);
//
//            for (int j = 0; j < lastCell; j++) {
//                XSSFCell cell = row.getCell(j);
//
//                System.out.println(cell);
//            }
//        }

//        String firstName = sheet.getRow(1).getCell(0).getStringCellValue();
//
//        double secondId = sheet.getRow(2).getCell(1).getNumericCellValue();
//
//        logger.info("First name from the first cell is: " + firstName);
//
//        logger.info("The second Id value is: " + secondId);


    }
    public String[][] readTestData() throws IOException {
        String excelFilePath = "test_data/TGTestData.xlsx";
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        int lastCell = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowCount][lastCell];

        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < lastCell; j++) {
                data[i-1][j] = row.getCell(j).getStringCellValue();
            }
        }

        return data;
    }

}
