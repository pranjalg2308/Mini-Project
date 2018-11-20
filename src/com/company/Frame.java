package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Frame extends JPanel implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    JFrame window;
    int size;
    char pressedKey = (char) 0;
    int gridSquareSize = 600;
    int sizeOfSquare = 0;
    Point[][] pointGridArray;
    Point startPoint = null;
    Point endPoint = null;
    Graphics g;
    Timer timer = new Timer(100, this);


    public Frame(int gridCount) {
        size = gridCount;
        pointGridArray = new Point[size][size];
        initialise();
        initialiseNeighbour();
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
        window.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        new Frame(20);
    }

    private void initialiseNeighbour() {
        int count = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                ArrayList<Point> neighbors = new ArrayList<Point>();
                try {
                    neighbors.add(pointGridArray[x][y + 1]);
                } catch (Exception e) {
                    count++;
                    System.out.println(e);
                }
                try {
                    neighbors.add(pointGridArray[x][y - 1]);
                } catch (Exception e) {
                    count++;
                    System.out.println(e);
                }
                try {
                    neighbors.add(pointGridArray[x + 1][y]);
                } catch (Exception e) {
                    count++;
                    System.out.println(e);
                }
                try {
                    neighbors.add(pointGridArray[x - 1][y]);
                } catch (Exception e) {
                    count++;
                    System.out.println(e);
                }
                pointGridArray[x][y].setNeighbors(neighbors);
            }
        }
        System.out.println(count);
    }

    public Point[][] getPointGridArray() {
        return this.pointGridArray;
    }

    private void initialise() {

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                pointGridArray[x][y] = new Point();
                pointGridArray[x][y].setPointStatus(Status.VIRGIN);
                pointGridArray[x][y].setXCoordinate(x);
                pointGridArray[x][y].setYCoordinate(y);
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
                    case Status.CHECKED:
                        g.setColor(Color.BLUE);
                        g.fillRect(i+1, j+1, sizeOfSquare-1, sizeOfSquare-1);
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

        } else if (SwingUtilities.isRightMouseButton(e)) {
            currentPoint.setPointStatus(Status.VIRGIN);
        }
        repaint();
        System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            if (startPoint != null && endPoint != null) {
                System.out.println("Done");
                Backend backend = new Backend(size, pointGridArray, startPoint, endPoint, this, g);
                if(!timer.isRunning()) {
                    timer.start();
                }
                backend.run();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        pressedKey = key;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKey = (char) 0;
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

    public void updateComponents() {
        this.repaint();
    }
}