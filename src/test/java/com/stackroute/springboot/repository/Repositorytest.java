package com.stackroute.springboot.repository;

import com.stackroute.springboot.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Repositorytest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setId(1);
        track.setName("believer");
        track.setComment("Imaginer");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(1,fetchTrack.getId());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(34,"senorita","shawn mendes");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTracks(){
        Track t = new Track(1,"senorita","shawn mendes");
        Track t1 = new Track(2,"Harry","new");
        trackRepository.save(t);
        trackRepository.save(t1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("senorita",list.get(0).getName());

    }
    @Test
    public void testUpdateComment(){
        trackRepository.save(track);
        track.setComment("good");
        trackRepository.save(track);
        String comment = (trackRepository.findById(3).get()).getComment();
        Assert.assertEquals("good", comment);
    }
    @Test
    public void testUpdateCommentFailure(){
        trackRepository.save(track);
        track.setComment("good");
        String comment = (trackRepository.findById(3).get()).getComment();
        Assert.assertNotEquals("good", comment);
    }
    @Test
    public void testGetTrackById(){

        Track track1 = new Track(10,"Nick","new");

        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Nick",list.get(0).getName());

    }
    @Test
    public void testGetTrackByIdFailure(){

        Track tracktest = new Track(10,"Nick","new");

        trackRepository.save(tracktest);

        List<Track> list = trackRepository.findAll();
        Assert.assertNotEquals("hgcvuhe",list.get(0).getName());

    }

}