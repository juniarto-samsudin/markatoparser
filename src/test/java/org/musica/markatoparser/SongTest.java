/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musica.markatoparser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juniarto
 */
public class SongTest {
    private static Song mySong;
    final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    
    public SongTest() throws IOException {
        mySong = new Song("C:\\Users\\juniarto\\Desktop\\IWannaHoldYourHand.txt");
    }

    /**
     * Test of getSongFile method, of class Song.
     */
    @Test
    public void testGetSongFile() {
        String actualSong = mySong.getSongFile();
        String expected = "C:\\Users\\juniarto\\Desktop\\IWannaHoldYourHand.txt";
        assertEquals(expected, actualSong);
    }
    
    
    
}
