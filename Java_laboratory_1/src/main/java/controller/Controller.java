/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ReadFile;
import model.Statistic;

/**
 *
 * @author Владислав
 */
public class Controller {
    private Statistic statistic;
    private HashMap<String, List<Double>> savedData;
    private ReadFile readFile;
    
    public Controller(){
        this.readFile = new ReadFile();
        new View(this);
        readFile();
    }
    
    public void readFile(){
        try{
            savedData = readFile.readXLSX();
            for (Map.Entry<String, List<Double>> entry : savedData.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());}
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}
