package com.stackroute.springboot.controller;

import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import com.stackroute.springboot.exceptions.TrackNotFound;
import com.stackroute.springboot.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@ControllerAdvice
@RequestMapping("api/v1")
public class TrackController {
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping("track")
    public ResponseEntity<Track> saveTrack(@RequestBody Track track)  {
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks()  {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping({"id"})
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id)  {
        ResponseEntity responseEntity;
        try {
            trackService.getTrackById(id);
            return new ResponseEntity<String>("tracked", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("track/{id}")
    public ResponseEntity<Track> deleteTrack(@PathVariable("id") int id)  {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>("deleted", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Track> UpdateTrack(@RequestBody Track track)  {
        ResponseEntity responseEntity;

        try {

            trackService.UpdateTrack(track);
            responseEntity= new ResponseEntity<String>("updated", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
