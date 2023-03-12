package apache_poi_excel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ReadingFromExcelFile {
    static Logger logger = LogManager.getLogger(ReadingFromExcelFile.class);

    public static void main(String[] args) throws IOException {
        String excelFilePath = "test_data/ReadData.xlsx";

        FileInputStream fileInputStream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        XSSFSheet sheet = workbook.getSheet("Sheet1");

        String firstName = sheet.getRow(1).getCell(0).getCellType().toString();

        double secondId = sheet.getRow(2).getCell(1).getNumericCellValue();

        logger.info("First name from the first cell is: " + firstName);

        logger.info("The second Id value is: " + secondId);


    }
}
