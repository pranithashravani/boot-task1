package com.stackroute.springboot.service;
import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import com.stackroute.springboot.exceptions.TrackNotFound;

import java.util.List;

public interface TrackService {
  public Track saveTrack(Track track) throws TrackAlreadyExists;
  public Track deleteTrack(int id) throws TrackNotFound;
  public  List<Track> getAllTracks() throws TrackNotFound;
  public Track getTrackById(int id) throws TrackNotFound;
  public Track UpdateTrack(Track track) throws TrackAlreadyExists;

}
