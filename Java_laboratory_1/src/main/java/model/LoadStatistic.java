/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Владислав
 */
public class LoadStatistic {
    public void loadXLSX(File file, Map<String, Object> stats) throws IOException{
        try(XSSFWorkbook excelBook = new XSSFWorkbook()){
            XSSFSheet sheet = excelBook.createSheet("Statistic");
             int rowNum = 0;
            
            
            for (Map.Entry<String, Object> entry : stats.entrySet()) {
                if (entry.getKey().equals("Ковариация")) continue;
                
                
                Row sectionRow = sheet.createRow(rowNum++);
                sectionRow.createCell(0).setCellValue(entry.getKey());
               
                
              
                Map<String, Object> metrics = (Map<String, Object>) entry.getValue();
                for (Map.Entry<String, Object> metric : metrics.entrySet()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(metric.getKey());
                    
                    Object value = metric.getValue();
                    Cell cell = row.createCell(1);
                    
                    
                    if (metric.getKey().equals("Среднее геометрическое")) {
                        if (value instanceof Double && !Double.isNaN((Double) value)) {
                            cell.setCellValue((Double) value);
                        } else {
                            cell.setCellValue("Недоступно");
                        }
                    }
                 
                    else if (metric.getKey().equals("Доверительный интервал") && value instanceof double[]) {
                        double[] interval = (double[]) value;
                            if (interval.length == 2) {
                                cell.setCellValue(interval[0] + " - " + interval[1]);
                            } else {
                                cell.setCellValue("Некорректные данные");
                            }
                    }
                    else if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    }
                    else {
                        cell.setCellValue(value.toString());
                    }
                }
                
                rowNum++; 
            }
            
            
            if (stats.containsKey("Ковариация")) {
                Row sectionRow = sheet.createRow(rowNum++);
                sectionRow.createCell(0).setCellValue("Ковариация");
                
                
                Map<String, Double> covariances = (Map<String, Double>) stats.get("Ковариация");
                for (Map.Entry<String, Double> cov : covariances.entrySet()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(cov.getKey());
                    row.createCell(1).setCellValue(cov.getValue());
                }
            }
            
            try(FileOutputStream fileout = new FileOutputStream(file)){
                excelBook.write(fileout);
                
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
