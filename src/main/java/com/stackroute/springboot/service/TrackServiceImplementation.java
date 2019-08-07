package com.stackroute.springboot.service;

import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import com.stackroute.springboot.exceptions.TrackNotFound;
import com.stackroute.springboot.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImplementation implements TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public TrackServiceImplementation(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    //implement all the methods
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExists {
        try {
            if (trackRepository.existsById(track.getId())) {
                throw new TrackAlreadyExists("already exists");
            }
            Track tracksave = trackRepository.save(track);
            if (tracksave == null)
                throw new TrackAlreadyExists("already exists");
            return track;
        } catch (Exception TrackAlreadyExists) {
            throw new TrackAlreadyExists("already exists");
        }
    }
    @Override
    public Track deleteTrack(int id) throws TrackNotFound {
        try {
            TrackRepository.deleteTrack(id);
            TrackService service = null;
            return service.getTrackById(id);
        }catch (Exception TrackNotFound)
        {
            throw new TrackNotFound("the track is not found");
        }
    }
    @Override
    public List<Track> getAllTracks() throws TrackNotFound {
        if (trackRepository.findAll() == null)
            throw new TrackNotFound("this track is not found");
        return trackRepository.findAll();
    }
    @Override
    public Track getTrackById(int id) throws TrackNotFound {
        Optional<Track> trackbyid = trackRepository.findById(id);
        if (trackbyid.isPresent()) {
            return trackbyid.get();
        } else
            throw new TrackNotFound("the track is not found");
    }
    @Override
    public Track UpdateTrack(Track track) throws TrackAlreadyExists {
        Track updatetrack = trackRepository.findById(track.getId()).get();
        try {
            if (updatetrack != null) {
                //Track newEntity = track.get();
                updatetrack.setId(track.getId());
                updatetrack.setName(track.getName());
                updatetrack.setComment(track.getComment());
                trackRepository.save(updatetrack);
                return updatetrack;
            } else {
                throw new TrackAlreadyExists("already exists");
            }
        } catch (Exception TrackAlreadyExists) {
            throw new TrackAlreadyExists("already exists");
        }
    }
}