package com.company;

import java.util.ArrayList;

public class Point {
    private int pointStatus;
    private int xCoordinate;
    private int yCoordinate;
    private ArrayList<Point> neighbors;
    private boolean isChecked;
    public Point() {
        this.pointStatus = Status.VIRGIN;
        this.xCoordinate = -1;
        this.yCoordinate= -1;
        this.isChecked = false;
        neighbors = new ArrayList<Point>();
    }

    public ArrayList<Point> getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(ArrayList<Point> neighbors){
        this.neighbors = neighbors;
    }

    public int getPointStatus() {
        return pointStatus;
    }

    public boolean isChecked(){
        return this.isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public void setPointStatus(int pointStatus) {
        this.pointStatus = pointStatus;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}