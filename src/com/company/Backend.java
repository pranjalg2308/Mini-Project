package com.company;
import java.util.*;

import static java.lang.System.*;
import static java.lang.Thread.sleep;

interface DFS {
    default boolean DFS(Point start, Point end, ArrayList<ArrayList<Point>> grid) {
        int gridSize = grid.size();
        Stack<Point> adjacentPoints = null; // Probably a debug point
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                out.println(grid.get(x).get(y).getXCoordinate() + " " + grid.get(x).get(y));
                if (grid.get(x).get(y).equals(end))
                    return true;
                if (!grid.get(x).get(y).isChecked()) {
                    adjacentPoints.add((grid.get(x).get(y)));
                    grid.get(x).get(y).setChecked(true);
                } else {
                    adjacentPoints.add((grid.get(x).get(y + 1)));
                    adjacentPoints.add((grid.get(x + 1).get(y)));
                    adjacentPoints.add((grid.get(x).get(y - 1)));
                    adjacentPoints.add((grid.get(x - 1).get(y)));
                }
            }
        }
        return false;
    }
}
// Inspired from hackerearth Tutorials
interface BFS {
    public static void BFSs(Point start, Point end, Point[][] grid,  int gridSize, Frame frame) {
        Stack<Point> queue = new Stack<Point>();
        queue.add(start);
        start.setPointStatus(Status.CHECKED);
        frame.repaint();
        while (!queue.isEmpty()) {
            Point current = queue.pop();
//            queue.remove(current);
            for (Point neighbor : current.getNeighbors()) {
                try {
                    if (neighbor.getPointStatus() != Status.OBSTACLE && neighbor.getPointStatus() != Status.CHECKED) {
                        queue.add(neighbor);
                        neighbor.setPointStatus(Status.CHECKED);
                        frame.repaint();
                        sleep((long) 1000);
                    }
                }
                catch (Exception e){
                    System.out.println(e);
                }
                try {
                    sleep((long)1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
public class Backend implements BFS {
    private static Point[][] grid;
    private static Point start;
    private static Point end;
    private static int gridSize;
    private static Frame frame;

    public Backend(int gridSize, Point[][] grid, Point start, Point end, Frame frame) {
//        grid = new Point[gridSize][gridSize];
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.gridSize = gridSize;
        this.frame = frame;
        main();
    }


    public static void main() {
            System.out.println("Hello");
            BFS.BFSs(start, end, grid, gridSize, frame);
        }
}
