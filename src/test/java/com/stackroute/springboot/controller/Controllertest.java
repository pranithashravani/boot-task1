package com.stackroute.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.springboot.domain.Track;
import com.stackroute.springboot.exceptions.TrackAlreadyExists;
import com.stackroute.springboot.exceptions.TrackNotFound;
import com.stackroute.springboot.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Controllertest {

    @Autowired
    private MockMvc mockmvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;
    private List<Track> list = null;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setId(1);
        track.setName("pranitha");
        track.setComment("hello");
        list = new ArrayList();
        list.add(track);
    }

    @Test
    public void saveTrack() throws Exception {
        when(trackService.saveTrack(any(Track.class))).thenReturn(true);
        mockmvc.perform(MockMvcRequestBuilders.post("/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveTrackFailure() throws Exception {

        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExists.class);
        mockmvc.perform(MockMvcRequestBuilders.post("/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllUser() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockmvc.perform(MockMvcRequestBuilders.get("/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }

}


}
