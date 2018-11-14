package com.company;

import javax.swing.*;
import java.awt.*;

public class Frame extends JPanel{
    JFrame window;
    public static void main(String[] args){
        new Frame();
    }

    public Frame() {
        window = new JFrame("Path Finding");
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(1200,700));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        window.add(BorderLayout.EAST,makeControlPanel());
        window.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private JPanel makeControlPanel() {
        JPanel controlPanel = new JPanel();
        return null;

    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        int height = getHeight();
        int width =  getWidth();
    }
}
