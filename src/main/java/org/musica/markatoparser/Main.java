/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.musica.markatoparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author juniarto
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStream is = new FileInputStream("C:\\Users\\juniarto\\Desktop\\yourhand.txt"); 
        Song mySong = new Song(is);
        //mySong.print();
        System.out.print(mySong.printString());
    }
    
}
