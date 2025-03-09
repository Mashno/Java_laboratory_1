/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;

/**
 *
 * @author Владислав
 */
public class ReadFile {
    private HashMap<String,Double[]> savedData;
    public ReadFile(){
        
    }
    
    public HashMap<String, Double[]> readXLSX(){
        savedData = new HashMap();
        return savedData;
    }
}
