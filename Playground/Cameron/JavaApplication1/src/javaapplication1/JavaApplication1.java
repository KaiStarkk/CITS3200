/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Cameron
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void writeVID(){
        String filename = "filename.vid";
        int startTime = 321563;
        int endTime = 382859;
        float diff = ((float)endTime - (float)startTime)/100;
        
        
        try {
          File file = new File(filename);
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          output.write("type=LOGAN\n" + "version=5.1.3\n");
          
          output.write("race=" + diff + ",0," + startTime + "," + endTime + ",\"H1S1\"" + "\n");
          
          output.write("IMFChecked=0\n");
          output.close();
        } catch ( IOException e ) {
           e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
        writeVID();
        System.out.println("finished");
    }
    
}
