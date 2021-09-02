package com.challenge.quasar.controllers;

import com.challenge.quasar.cache.CacheConfig;
import com.challenge.quasar.entities.Location;
import com.challenge.quasar.entities.Satellite;
import com.challenge.quasar.entities.SatelliteList;
import com.challenge.quasar.entities.Spaceship;
import com.challenge.quasar.services.LocationService;
import com.challenge.quasar.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AppController {

    private final LocationService locationService;
    private final MessageService messageService;
    private final CacheConfig cache;

    @Autowired
    private Environment environment;

    @Autowired
    public AppController(LocationService locationService, MessageService messageService, CacheConfig cache) {
        this.locationService = locationService;
        this.messageService = messageService;
        this.cache = cache;
    }

    @PostMapping(path = "/topsecret", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(@RequestBody SatelliteList satelliteList) throws ResponseStatusException {
        try {
            if (satelliteList.getSatellites().size() < 3) {
                throw new Exception("Se precisa al menos 3 satélites para determinar con precisión la ubicación");
            }
            Location spaceshipLocation = locationService.getLocation(satelliteList.getSatellites());
            String spaceshipMessage = messageService.decodeMessage(satelliteList.obtainMessages());
            Spaceship spaceship = new Spaceship(spaceshipLocation, spaceshipMessage);
            return ResponseEntity.status(HttpStatus.OK).body(spaceship);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("topsecret_split/{satellite_name}")
    public ResponseEntity topSecretSplit(
            @PathVariable String satellite_name,
            @RequestParam(value="distance") String distanceParam,
            @RequestParam(value="message") String messageParam
    ) throws ResponseStatusException {
        try {
            double distance = Double.parseDouble(distanceParam);
            String[] message = messageParam.split(";");
            Satellite satellite = new Satellite(satellite_name, distance, message);
            String[] satellitesNames = this.environment.getProperty("satellites.names").split(",");
            if (Arrays.stream(satellitesNames).noneMatch(elt -> elt.equals(satellite_name))) {
                throw new Exception("Nombre de satelite no configurado");
            }
            List<Satellite> cachedSatelliteList = new ArrayList<Satellite>();
            satellite.setName(satellite_name);
            this.cache.cacheManager().getCache("satellitesCache").put(satellite.getName(), satellite);
            for (String satelliteName : satellitesNames) {
                Cache.ValueWrapper cache = this.cache.cacheManager().getCache("satellitesCache").get(satelliteName);
                if (cache != null) {
                    Satellite cachedSatellite = (Satellite) cache.get();
                    cachedSatelliteList.add(cachedSatellite);
                }
            }
            return this.topSecret(new SatelliteList(cachedSatelliteList));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}