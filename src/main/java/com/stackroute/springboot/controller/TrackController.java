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
@RequestMapping(value="api/v1")
public class TrackController {
    TrackService trackService;
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllUsers(){
        ResponseEntity responseEntity;
        try{
            trackService.getAllTracks();
            responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        } catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try {
            trackService.getTrackById(id);
            responseEntity=new ResponseEntity<String>("track found", HttpStatus.OK);
        } catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteTrack")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.deleteTrack(track.getId());
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        }
        catch(Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @PutMapping("track/{id}")
    public ResponseEntity<Track> UpdateTrack(@RequestBody Track track){
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
