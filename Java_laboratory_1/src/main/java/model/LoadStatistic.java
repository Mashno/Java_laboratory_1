/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Владислав
 */
public class LoadStatistic {
    public void loadXLSX(File file, ArrayList<Double> stats) throws IOException{
        try(XSSFWorkbook excelBook = new XSSFWorkbook()){
            XSSFSheet sheet = excelBook.createSheet("Statistic");
            for(int i = 0; i < 10; i++){
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(stats.get(i));
            }
            
            try(FileOutputStream fileout = new FileOutputStream(file)){
                excelBook.write(fileout);
                
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
