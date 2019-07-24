package com.stackroute.springboot.service;
import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import com.stackroute.springboot.exceptions.TrackNotFound;

import java.util.List;

public interface TrackService {
    public boolean saveTrack(Track track) throws TrackAlreadyExists;

    public boolean deleteTrack(int id) throws TrackNotFound;

    public List<Track> getAllTracks() throws TrackNotFound;

    public Track getTrackById(int id) throws TrackNotFound;

    public boolean UpdateTrack(Track track) throws TrackAlreadyExists;
    public List<Track> getTrackByName(String name);

}
