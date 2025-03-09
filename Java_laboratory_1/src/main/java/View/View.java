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
import javax.swing.*;

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
                
            }
            
        });
        
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
            
        });
    }
}
