/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import View.View;
import java.util.HashMap;
import model.ReadFile;
import model.Statistic;

/**
 *
 * @author Владислав
 */
public class Controller {
    private Statistic statistic;
    private HashMap<String, Double[]> savedData;
    private ReadFile readFile;
    
    public Controller(){
        new View(this);
    }
    
    public void readFile(){
        savedData = readFile.readXLSX();
    }
    
    
}
