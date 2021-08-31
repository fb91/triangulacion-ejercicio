package com.challenge.quasar.entities;

import java.util.ArrayList;
import java.util.List;

public class SatelliteList {
    private List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public SatelliteList() {
        super();
    }

    public SatelliteList(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<String[]> obtainMessages() {
        List<String[]> messages = new ArrayList<String[]>();
        this.satellites.forEach((final Satellite s) -> messages.add(s.getMessage()));
        return messages;
    }
}
