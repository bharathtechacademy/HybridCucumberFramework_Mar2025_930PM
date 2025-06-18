package com.orangehrm.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for handling Excel files.
 * Provides methods to read data from Excel sheets and return it in various formats.
 */
public class ExcelUtil {

    /**
     * Reads data from a specified Excel sheet and returns it as a list of maps.
     * Each map represents a row, with column names as keys and cell values as values.
     *
     * @param fileName The name of the Excel file to be read.
     *                 The file should be located in the `TestData` directory under the user's working directory.
     * @param sheetName The name of the sheet within the Excel file to read data from.
     * @return A list of maps, where each map contains key-value pairs representing column names and their corresponding cell values.
     *         If the file is not found or an error occurs during reading, an empty list is returned.
     * @throws FileNotFoundException If the specified file is not found.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static List<Map<String, String>> readData(String fileName, String sheetName) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        try {
            // Read the Excel file using Java
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

            // Load the file into Excel-related classes
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Read the data from the specified sheet
            XSSFSheet sh = wb.getSheet(sheetName);

            // Get the total rows and columns having data
            int totalRows = sh.getPhysicalNumberOfRows();
            int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();

            // Loop to iterate over rows
            for (int r = 1; r < totalRows; r++) {
                Map<String, String> rowData = new HashMap<String, String>();

                // Loop to iterate over columns
                for (int c = 0; c < totalColumns; c++) {
                    String columnName = sh.getRow(0).getCell(c).getStringCellValue();
                    String columnValue = sh.getRow(r).getCell(c).getStringCellValue();
                    rowData.put(columnName, columnValue);
                }

                data.add(rowData);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Reads data from a specified Excel sheet and returns it as a 2D array of strings.
     * Each row in the array represents a row in the sheet, and each column represents a cell value.
     *
     * @param fileName The name of the Excel file to be read.
     *                 The file should be located in the `TestData` directory under the user's working directory.
     * @param sheetName The name of the sheet within the Excel file to read data from.
     * @return A 2D array of strings containing the data from the specified sheet.
     *         If the file is not found or an error occurs during reading, a null array is returned.
     * @throws FileNotFoundException If the specified file is not found.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static String[][] readExcelData(String fileName, String sheetName) {
        String[][] data = null;

        try {
            // Read the Excel file using Java
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + fileName);

            // Load the file into Excel-related classes
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            // Read the data from the specified sheet
            XSSFSheet sh = wb.getSheet(sheetName);

            // Get the total rows and columns having data
            int totalRows = sh.getPhysicalNumberOfRows();
            int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();

            // Create a 2D array to store the data
            data = new String[totalRows-1][totalColumns];

            // Loop to iterate over rows
            for (int r = 1; r < totalRows; r++) {
                // Loop to iterate over columns
                for (int c = 0; c < totalColumns; c++) {
                    data[r-1][c] = sh.getRow(r).getCell(c).getStringCellValue();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
