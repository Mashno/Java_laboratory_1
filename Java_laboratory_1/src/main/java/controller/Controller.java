/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.View;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.CountStatistic;
import model.LoadStatistic;
import model.ReadFile;

/**
 *
 * @author Владислав
 */
public class Controller {
    private Map<String, Object> statistic;
    private HashMap<String, List<Double>> savedData;
    private ReadFile readFile;
    private CountStatistic countStatistic;
    private LoadStatistic loadStatistic;
    
    public Controller(){
        this.readFile = new ReadFile();
        this.countStatistic = new CountStatistic();
        this.loadStatistic = new LoadStatistic();
        this.statistic = new HashMap<>(); 
        new View(this);
        
    }
    
    public void readFile(File selectedFile){
        try{
            savedData = readFile.readXLSX(selectedFile);
            for (Map.Entry<String, List<Double>> entry : savedData.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());}
            
            countStatistic();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadStatistic(File file){
        try{
            loadStatistic.loadXLSX(file, statistic);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void countStatistic(){
        statistic = countStatistic.countXLSX(savedData);
    }
    
    
}
