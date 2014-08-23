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
 

    //possible set 0-time,1-gps,2-accelerometer
    Column[] columns;
    /**
     * Constructor.
     * Constructs the array of columns which will then 
     * be passed to the Analyser.
     * 
     * @param path the path to read .csv data from.
     */
    public DataSet(String path) {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(path));
            
        }
        catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}
        finally{

            try{
                String current;
                while((current =reader.readLine()) != null){
                    String[] contents = current.split(",");
                    columns[0].add(contents[0]);
                    columns[1].add(contents[1]);
                    columns[2].add(contents[2]);
                reader.close();
                }
            }catch (IOException e) {
		e.printStackTrace();
            }
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
