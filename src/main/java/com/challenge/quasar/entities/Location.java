package com.challenge.quasar.entities;

public class Location {

    private double x;
    private double y;

    public Location(String[] xy) {
        this.x = Double.parseDouble(xy[0]);
        this.y = Double.parseDouble(xy[1]);
    }

    public Location(double[] xy) {
        this.x = xy[0];
        this.y = xy[1];
    }

    public double getX() {
        return x;
    }

    public double obtainNegatedX() {
        return -x;
    }

    public double obtainNegatedY() {
        return -y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public double[] asArray() {
        double[] location = {this.x, this.y};
        return location;
    }
}
