package com.challenge.quasar.controllers;

import com.challenge.quasar.entities.Location;
import com.challenge.quasar.entities.SatelliteList;
import com.challenge.quasar.entities.Spaceship;
import com.challenge.quasar.services.LocationService;
import com.challenge.quasar.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AppController {

    private final LocationService locationService;
    private final MessageService messageService;

    @Autowired
    public AppController(LocationService locationService, MessageService messageService) {
        this.locationService = locationService;
        this.messageService = messageService;
    }

    @PostMapping(path = "/topsecret", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(@RequestBody SatelliteList satelliteList) {
        try {
            Location spaceshipLocation = locationService.getLocation(satelliteList.getSatellites());
            String spaceshipMessage = messageService.decodeMessage(satelliteList.obtainMessages());
            Spaceship spaceship = new Spaceship(spaceshipLocation, spaceshipMessage);
            return ResponseEntity.status(HttpStatus.OK).body(spaceship);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
        }
    }
}