package com.challenge.quasar.entities;

public class Spaceship {
    private Location position;
    private String message;

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Spaceship(Location position, String message) {
        this.position = position;
        this.message = message;
    }
}
