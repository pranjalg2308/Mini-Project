package com.company;

public class Point {
    private int pointStatus;
    private int xCoordinate;
    private int yCoordinate;
    private boolean isChecked;
    public Point() {
        this.pointStatus = Status.VIRGIN;
        this.xCoordinate = -1;
        this.yCoordinate= -1;
        this.isChecked = false;
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