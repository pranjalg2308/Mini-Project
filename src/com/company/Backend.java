
//import Point;
//import Status;
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
        ArrayList<Point> queue = new ArrayList<Point>();
        queue.add(start);
        frame.repaint();
        while (!queue.isEmpty()) {
            Point current = queue.remove(0);
            if(current.getNeighbors().equals(null))
                continue;
            for (Point neighbor : current.getNeighbors()) {
                try {
                    if(neighbor.getPointStatus()==Status.END){
                    queue.clear();
                    break;
                }
                    if (neighbor.getPointStatus() != Status.OBSTACLE && neighbor.getPointStatus() != Status.CHECKED) {
                        queue.add(neighbor);
                        
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
public class Backend implements BFS, Runnable{
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
    }

    @Override
    public void run() {
        BFS.BFSs(start, end, grid, gridSize, frame);
    }
}
