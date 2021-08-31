package com.challenge.quasar.entities;

import java.util.Arrays;

public class Satellite {
    private String name;
    private double distance;
    private String[] message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public Satellite(String name, double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
