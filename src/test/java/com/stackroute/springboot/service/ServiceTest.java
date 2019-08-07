package com.stackroute.springboot.service;

import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackNotFound;
import com.stackroute.springboot.repository.TrackRepository;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceTest {
    Track track;
    @Mock
    TrackRepository trackRepository;
    @InjectMocks
    TrackServiceImplementation trackService;
    List<Track> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(1);
        track.setName("believer");
        track.setComment("Imaginer");
        list = new ArrayList<>();
        list.add(track);

    }
    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExists{

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        assertEquals(track,savedUser);
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExists.class)
    public void saveUserTestFailure() throws TrackAlreadyExists {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track saveTrack = trackService.saveTrack(track);
        System.out.println("savedUser" + saveTrack);
        assertEquals(track,saveTrack);

       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }

    @Test
    public void getAlltracks() throws TrackNotFound{

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        assertEquals(list,userlist);
    }
    @Test
    public void deleteTrack()throws TrackNotFound {
        trackRepository.deleteById(track.getId());
        verify(trackRepository).deleteById(anyInt());
    }
    @Test
    public void updateTrackTest() throws TrackNotFound
    {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track updateTrack = null;
        try {
            updateTrack = trackService.saveTrack(track);
        } catch (TrackAlreadyExists e) {
            e.printStackTrace();
        }
        assertEquals(track,updateTrack);
    }
    @Test
    public void getTrackById() throws TrackNotFound{



        when(trackRepository.findAllById(Collections.singleton(1))).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        assertEquals(list,userlist);
    }
}