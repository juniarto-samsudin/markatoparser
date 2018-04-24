/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musica.markatoparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author juniarto
 */
public class Song {
    private static InputStream SongFile;
    private List<String> FinalSongFile;
    private String temp;
    private String tempString;
    private String SongTitle;
    
    public Song(InputStream is) throws IOException{
           
           SongFile = is; 
           FinalSongFile = new ArrayList<>();
           this.MainProcess();
    }
    
    public String getSongFile(){
        return SongTitle;
    }
    
    public void print(){
        FinalSongFile.forEach((temp) -> {
            System.out.println(temp);
        });
    }
    
    public String printString(){
        StringBuilder myBuilder = new StringBuilder();
        for (int i = 0 ; i < FinalSongFile.size();i++){
            myBuilder.append(FinalSongFile.get(i));
            myBuilder.append("\n");
        }
        return myBuilder.toString();
        }
    
    
    private void MainProcess() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(SongFile));
        String line;
        while ((line = br.readLine()) != null){
            if (processLine(line) != null){
                if (processLine(line).equals("CHORD")){
                    continue;
                }else{
                    FinalSongFile.add(processLine(line));
                }
            }
        }
    }
    
    private String processLine(String line){
        String newLine = null;
        if (line.subSequence(0, 2).equals("##")){
            newLine = line.substring(2);
        }else if (line.subSequence(0, 1).equals("#")){
            newLine = line.substring(1);
        }else if (line.subSequence(0, 1).equals(":")){
            temp = line.substring(1);
            newLine = "CHORD";
        }else{
            newLine = processLyric(temp,line);
        }
        return newLine;
    }
    
    private String processLyric(String chord, String lyric){
        //Process Chord 
        String[] chordArray = null;
        if (chord != null){
            chordArray = chord.split(" ");
        }else{
            return "Wello";
        }
        
        //Process Lyric
        List<Integer> position = new ArrayList<>();
        StringBuilder builder = new StringBuilder(lyric);
        
        int count = 0;
        int lyric_length = lyric.length();
        char c;
      
        while (count < lyric_length){
            c = builder.charAt(count);
            if ( c == '^'){
                position.add(count);
                builder.deleteCharAt(count);
                lyric_length = builder.toString().length();
                continue;
            }
            count++;
        }
        lyric = builder.toString();
        
        //process both
        StringBuilder newLyric = new StringBuilder();
        outer:
        for (int i=0; i < lyric.length(); i++){
            for (int j=0; j < position.size(); j++){
                if (i == position.get(j)){
                    newLyric.append(chordArray[j]);
                    continue outer;
                }
            }
            newLyric.append(" ");
        }
        newLyric.append("\n");
        newLyric.append(lyric);
        
        return newLyric.toString();
    }
    
    
}
