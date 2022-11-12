package com.Bank.utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelReader {

    private ExcelReader() {
        System.out.println(" >> Excel Reader INIT Successs");
    }


    public static Sheet getWorkbook(String excelFilePath) throws Exception {
        Workbook workbook = null;
        System.out.println("Reading excel file" + excelFilePath);
        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            inputStream.available();
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Could not find file " + excelFilePath);
        } catch (IOException e) {
            throw new Exception("Error accessing file " + excelFilePath);
        }
        if (workbook != null) {
            return workbook.getSheetAt(0);
        } else {
            return null;
        }
    }

    /**
     * use this to read in List
     *
     * @param sh
     * @return
     */
    public static List<String> getExcelData(Sheet sh) {
        Row row = null;
        List<String> rowValue = new ArrayList<>();
        row = sh.getRow(0);
        for (int iCount = 0; iCount < row.getLastCellNum(); iCount++) {
            rowValue.add(row.getCell(iCount).getStringCellValue().trim());
        }
        return rowValue;
    }

    /**
     * use this to read all excel in array
     *
     * @param sh
     * @return
     */
    public static String[][] readExcelData(Sheet sh) {
        String[][] data = null;
        int rowNum = sh.getLastRowNum();
        int colNum = sh.getRow(0).getLastCellNum();
        data = new String[rowNum][colNum];
        int iCount = 0;
        for (int iRow = 1; iRow <= rowNum; iRow++) {
            Row row = sh.getRow(iRow);
            for (int jCol = 0; jCol < colNum; jCol++) {
                Cell cell = row.getCell(jCol);
                String value = new DataFormatter().formatCellValue(cell);
                if (value.equals("")) {
                    value = "";
                }
                data[iCount][jCol] = value;
                System.out.println(">> Values are : " + value);
            }
            iCount++;
        }
        return data;
    }

    // to add for Hasmap if data is in single line
    public static HashMap readExcelUsingHashMap(Sheet sh) {
        return null;
    }
}
