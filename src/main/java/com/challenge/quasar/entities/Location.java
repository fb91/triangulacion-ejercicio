package com.challenge.quasar.entities;

public class Location {

    private double x;
    private double y;

    public Location(String[] xy) {
        this(new double[]{Double.parseDouble(xy[0]), Double.parseDouble(xy[1])});
    }

    public Location(double[] xy) {
        //Rounding to 2 decimal
        this.x = (double) Math.round(xy[0]*10)/10;
        this.y = (double) Math.round(xy[1]*10)/10;
    }

    public double getX() {
        return x;
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
