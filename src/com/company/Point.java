package com.company;

public class Point {
    private int pointStatus;
    private int xCoordinate;
    private int yCoordinate;

    public Point() {
        this.pointStatus = Status.VIRGIN;
        this.xCoordinate = -1;
        this.yCoordinate= -1;

    }

    public int getPointStatus() {
        return pointStatus;
    }

    public void setPointStatus(int pointStatus) {
        this.pointStatus = pointStatus;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
