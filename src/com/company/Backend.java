package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

interface DFS {
    public static void BFSs(Point start, Point end, Point[][] grid,  int gridSize, Frame frame) {
        Stack<Point> stack = new Stack<Point>();
        stack.add(start);
        frame.repaint();
        while (!stack.isEmpty()) {
            Point current = stack.remove(0);
            for (Point neighbor : current.getNeighbors()) {
                try {
                    if(neighbor.getPointStatus()==Status.END){
                    stack.clear();
                    break;
                }
                    if (neighbor.getPointStatus() != Status.OBSTACLE && neighbor.getPointStatus() != Status.CHECKED) {
                        stack.add(neighbor);
                        
                        if(neighbor.getPointStatus()==Status.END || neighbor.getPointStatus()==Status.START)
                            continue;
                        neighbor.setPointStatus(Status.CHECKED);
                        sleep((long)100);
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}

// Inspired from hackerearth Tutorials
interface BFS {
    public static void BFSs(Point start, Point end, Point[][] grid, int gridSize, Frame frame, Graphics g) {
        ArrayList<Point> queue = new ArrayList<Point>();
        queue.add(start);
        frame.repaint();
        while (!queue.isEmpty()) {
            Point current = queue.remove(0);
            for (Point neighbor : current.getNeighbors()) {
                try {
                    if (neighbor.getPointStatus() == Status.END) {
                        queue.clear();
                        break;
                    }
                    if (neighbor.getPointStatus() != Status.OBSTACLE && neighbor.getPointStatus() != Status.CHECKED) {
                        queue.add(neighbor);
                        if (neighbor.getPointStatus() == Status.END || neighbor.getPointStatus() == Status.START)
                            continue;
                        neighbor.setPointStatus(Status.CHECKED);
//                        frame.paintComponent(g);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}

public class Backend implements BFS, Runnable {
    private static Point[][] grid;
    private static Point start;
    private static Point end;
    private static int gridSize;
    private static Frame frame;
    private static Graphics g;


    public Backend(int gridSize, Point[][] grid, Point start, Point end, Frame frame, Graphics g) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.gridSize = gridSize;
        this.frame = frame;
        this.g = g;
    }

    @Override
    public void run() {
        BFS.BFSs(start, end, grid, gridSize, frame,g);
        frame.repaint();
    }
}
