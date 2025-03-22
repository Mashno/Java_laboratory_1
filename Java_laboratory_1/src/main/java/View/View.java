/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Владислав
 */
public class View extends JFrame{
    private Controller controller;
    private JButton addFileButton;
    private JButton exitButton;
    private JButton loadFileButton;
    private JFrame frame;
    private JPanel panel;
    private JFileChooser fileChooser;
    private File selectedFile;
    private int selectedSheetIndex = -1;
    
    public View(Controller controller){
        this.controller = controller;
        initialize();
        
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        
        
        panel = new JPanel(new GridLayout(3,1));
        addFileButton = new JButton("add file");
        exitButton = new JButton("exit");
        loadFileButton = new JButton("load file");
        
        panel.add(addFileButton);
        panel.add(loadFileButton);
        panel.add(exitButton);
        frame.add(panel, BorderLayout.CENTER);
        
        frame.setVisible(true);
        
        
        addFileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files (.xlsx)", "xlsx"));
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    try (Workbook workbook = new XSSFWorkbook(selectedFile)) {
                        int sheetCount = workbook.getNumberOfSheets();
                        String[] sheetNames = new String[sheetCount];
                        for (int i = 0; i < sheetCount; i++) {
                            sheetNames[i] = workbook.getSheetName(i);
                        }

                        
                        String selectedSheetName = (String) JOptionPane.showInputDialog(
                                frame,
                                "Выберите лист для импорта данных:",
                                "Выбор листа",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                sheetNames,
                                sheetNames[0]);

                        if (selectedSheetName != null) {
                            for (int i = 0; i < sheetNames.length; i++) {
                                if (sheetNames[i].equals(selectedSheetName)) {
                                    selectedSheetIndex = i;
                                    break;
                                }
                            }
                            controller.readFile(selectedFile, selectedSheetIndex);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Ошибка при чтении файла: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        });
        
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
            
        });
        
        loadFileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                
                int result = fileChooser.showSaveDialog(null);
                
                if( result == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".xlsx")) {
                        file = new File(file.getAbsolutePath() + ".xlsx");
                    }
                    
                    controller.loadStatistic(file);
                }
            }
            
        });
    }
}
