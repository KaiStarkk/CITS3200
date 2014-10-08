/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Grounds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Markcuz
 */
public class GroundsIO {
    
    List<String[]> groundsList;
    
    public GroundsIO(File groundsFile) {
        groundsList = new ArrayList<>();
        loadGrounds(groundsFile);  
    }
    
    /**
     * opens the grounds txt file and adds to the list
     * @param groundsFile
     */
    final public void loadGrounds(File groundsFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(groundsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] current = line.split("\\t");

                boolean add = groundsList.add(current);
            }
        }
        catch (IOException e){
            System.err.println("Could not open file.");
        }
    }
    
    /**
     * returns the list for grounds
     * @return groundsList
     */
    public final List<String[]> getGroundsDB() {
        return groundsList;
    }
    
    /**
     * Prints a database to standard output.
     */
    public final void printDB() {
        List<String[]> temp = groundsList;
        if (!temp.isEmpty()) {
            temp.stream().forEach((entry) -> {
                System.out.println(Arrays.toString(entry));
            });
            System.out.println();
        } else {
            System.out.println("Grounds database is empty.\n");
        }
    }
    
    /**
     * selects the given ground and constructs the Ground
     * @param name
     * @return the ground required
     */
    public Ground chooseGround(String name) {
        
        int i = 0;
        
        if(i!=groundsList.size()&&(!groundsList.isEmpty())) {
            while(!groundsList.get(i)[8].equals(name)) {
                i++;
            }
        }
        else {
            System.out.println("Could not find ground"+name);
        }
        
        String[] groundStrings = groundsList.get(i);
        GPSCoordinate gps1 = new GPSCoordinate(Double.parseDouble(groundStrings[1]), Double.parseDouble(groundStrings[0]));
        
        GPSCoordinate gps2 = new GPSCoordinate(Double.parseDouble(groundStrings[4]), Double.parseDouble(groundStrings[3]));
       
        double aspectRatio = Double.parseDouble(groundsList.get(i)[6]);
        
        Ground ground = new Ground(name, gps1, gps2, aspectRatio);
        
        return ground;  
    }
    
    /**
     * outputs an array of strings for use in UI
     * @return array of groundNames
     */
    public final String[] groundNames() {
        
        String[] out = new String[groundsList.size()];
        
        if (!groundsList.isEmpty()) {
            for(int i = 0; i<groundsList.size(); i++) {
                out[i] = groundsList.get(i)[8];
            } 
        } 
        else {
            System.out.println("Grounds database is empty.\n");
        }
        
        return out;
    }
    
}
