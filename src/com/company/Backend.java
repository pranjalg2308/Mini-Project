package com.company;
import java.util.*;

import static java.lang.System.*;

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
interface BFS {
    static boolean BFSs(Point start, Point end, Point grid[][], int gridSize) {
        PriorityQueue<Point> adjacentPoints = null; // Probably a debug point
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (grid[x][y].equals(end))
                    return true;
                if (!grid[x][y].isChecked() && grid[x][y].getPointStatus()!=3) {
                    grid[x][y].setPointStatus(Status.CHECKED);
                } else {
                    adjacentPoints.add((grid[x][y+1]));
                    adjacentPoints.add((grid[x+1][y]));
                    adjacentPoints.add((grid[x][y-1]));
                    adjacentPoints.add((grid[x-1][y]));
                }
            }
        }
        return false;
    }
}
public class Backend implements BFS {
    private static Point[][] grid;
    private static Point start;
    private static Point end;
    private static int gridSize;

    public Backend(int gridSize, Point[][] grid, Point start, Point end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.gridSize = gridSize;
    }

    public static void main(String[] args) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                grid[x][y].setXCoordinate(x + 1);
                grid[x][y].setYCoordinate(y + 1);
            }
            System.out.println(BFS.BFSs(start, end, grid, gridSize));
        }
    }
}
