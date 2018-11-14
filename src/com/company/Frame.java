package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    JFrame window;
    int size;
    char pressedKey = (char) 0;
    int gridSquareSize = 600;
    int sizeOfSquare = 0;
    Point[][] pointGridArray;
    Point startPoint = null;
    Point endPoint = null;

    public Frame(int gridCount) {
        size = gridCount;
        pointGridArray = new Point[size][size];
        initialise();
        window = new JFrame("Path Finding");
        window.setContentPane(this);
        window.getContentPane().setPreferredSize(new Dimension(800, 600));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
//        window.add(BorderLayout.EAST,makeControlPanel());
        window.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        new Frame(20);
    }

    private void initialise() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pointGridArray[i][j] = new Point();
            }
        }
    }

    private JPanel makeControlPanel() {
        JPanel controlPanel = new JPanel();
        return null;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grab dimensions of panel
        int height = getHeight();
        int width = getWidth();
        // Draws grid
        sizeOfSquare = gridSquareSize / size;
        g.setColor(Color.lightGray);
        for (int j = 0; j < gridSquareSize; j += sizeOfSquare) {
            for (int i = 0; i < gridSquareSize; i += sizeOfSquare) {
                Point currentPoint = pointGridArray[i / sizeOfSquare][j / sizeOfSquare];
                switch (currentPoint.getPointStatus()) {
                    case Status.OBSTACLE:
                        //draw border
                        g.setColor(Color.black);
                        g.fillRect(i + 1, j + 1, sizeOfSquare - 1, sizeOfSquare - 1);
                        break;
                    case Status.VIRGIN:
                        //draw Virgin Point
                        g.setColor(Color.white);
                        g.drawRect(i, j, sizeOfSquare, sizeOfSquare);
                        break;
                    case Status.START:
                        //draw Start Point
                        g.setColor(Color.red);
                        g.fillRect(i + 1, j + 1, sizeOfSquare - 1, sizeOfSquare - 1);
                        break;
                    case Status.END:
                        //draw End Point
                        g.setColor(Color.GREEN);
                        g.fillRect(i + 1, j + 1, sizeOfSquare - 1, sizeOfSquare - 1);
                        break;
                }
            }
        }
    }

    private void MapDraw(MouseEvent e) {
        int xBorder = e.getX() - (e.getX() % sizeOfSquare);
        int yBorder = e.getY() - (e.getY() % sizeOfSquare);
        int xCoordinate = xBorder / sizeOfSquare;
        int yCoordinate = yBorder / sizeOfSquare;
        Point currentPoint = pointGridArray[xCoordinate][yCoordinate];
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (pressedKey == 's') {
                currentPoint.setPointStatus(Status.START);
                if (startPoint != null)
                    startPoint.setPointStatus(Status.VIRGIN);
                startPoint = currentPoint;
            } else if (pressedKey == 'e') {
                currentPoint.setPointStatus(Status.END);
                if (endPoint != null)
                    endPoint.setPointStatus(Status.VIRGIN);
                endPoint = currentPoint;
            } else
                currentPoint.setPointStatus(Status.OBSTACLE);
            repaint();
        } else if (SwingUtilities.isRightMouseButton(e)) {
            currentPoint.setPointStatus(Status.VIRGIN);
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        pressedKey = key;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MapDraw(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MapDraw(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
