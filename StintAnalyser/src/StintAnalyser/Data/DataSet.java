/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author Kieran
 */
public class DataSet {
 

    //vid file type
    public String type;

    //file version
    public String version;

    //possible set 0-time,1-gps,2-accelerometer
    private Column[] columns;
    /**
     * Constructor.
     * Constructs the array of columns which will then 
     * be passed to the Analyser.
     * 
     * @param path the path to read .csv data from.
     */
    public DataSet(String path) throws Exception{
    
        try{

            BufferedReader reader = new BufferedReader(new FileReader(path));

            String firstline = reader.readLine();
            String[] fsplit = firstline.split(" ");

            type = fsplit[0];
            version = fsplit[1];

            for(int i = 1; i < 7;i++){
                reader.readLine();
            }
            String check = reader.readLine();
            //String checker = "Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longitude, /n";

            //exception will be changed
            if(!check.equals("Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longitude, /n")){
                //file invalid error 
                throw new Exception();

            } 

            String current;
            columns = new Column[5];

            while((current =reader.readLine()) != null){

                String[] contents = current.split(",");

                if(contents.length>5){

                    throw new Exception();

                }

                //third element of contents

                String check= contents[3].trim();

                if(check.equals("..")){
                    continue;
                }

                for(int i=0;i<5;i++){

                    String content = contents[i].trim();
                    columns[i].add(content);

                }

            reader.close();
            
            }
            
        }
        catch (FileNotFoundException e) {
		e.printStackTrace();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}

    }
    
    /**
     * Accessor for each column of the data.
     * @param num An integer representation of the type of
     * data stored in the column
     * @return 
     */
    public Column getColumn(int num) {
        
        return columns[num];
    }
    
}
