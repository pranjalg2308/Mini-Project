package com.company;
import javax.swing.*;
public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame();
        JButton button = new JButton("Touch me na");
        button.setBounds(10,100,100,200);
        frame.setSize(300,300);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
