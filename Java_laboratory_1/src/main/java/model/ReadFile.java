/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Владислав
 */
public class ReadFile {
    private HashMap<String,List<Double>> savedData;
    public ReadFile(){
        
    }
    
    public HashMap<String, List<Double>> readXLSX(File selectedFile, int selectedSheetIndex) throws IOException{
        savedData = new HashMap();
        
        XSSFWorkbook excelBook = new XSSFWorkbook(new FileInputStream(selectedFile));
        XSSFSheet excelSheet = excelBook.getSheetAt(selectedSheetIndex);
        XSSFRow headerRow = excelSheet.getRow(0);
        int numColumn = headerRow.getLastCellNum();
        
        
        for (int c = 0; c < numColumn; c++) {
                XSSFCell cell = headerRow.getCell(c);
                String columnName = cell.getStringCellValue(); // Название столбца
                savedData.put(columnName, new ArrayList<>()); // Создаем пустой список чисел
            }

            // Читаем строки, начиная со второй (индекс 1)
            for (int r = 1; r <= excelSheet.getLastRowNum(); r++) {
                XSSFRow row = excelSheet.getRow(r);

                for (int c = 0; c < numColumn; c++) {
                    XSSFCell cell = row.getCell(c);
                    String columnName = headerRow.getCell(c).getStringCellValue(); // Определяем столбец

                    if (cell.getCellType() == CellType.NUMERIC) {
                        savedData.get(columnName).add(cell.getNumericCellValue()); // Добавляем число в список
                    }
                }
            }
        return savedData;
    }
}
