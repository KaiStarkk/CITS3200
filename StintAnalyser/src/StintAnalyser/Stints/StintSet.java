package StintAnalyser.Stints;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CITS3200 Professional Computing
 * StintSet.java
 * Contains fields and methods pertaining to StintSets. StintSets are 
 * an array of Stints.
 * 
 * @version 1.1 20/08/14
 * @author Group B
 */
public class StintSet {
    
    private ArrayList<Stint> stintSet;
    
    /**
     * Empty constructor.
     */
    public StintSet() {
        stintSet = new ArrayList<Stint>();
        
    }
    
    /**
     * Writes the StintSet out to a .vid file.
     * @param path path to write the file to.
     */
    public void writeToVid(String path,String type,String version) {
        
        try{
            //missing star
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            String first = "type="+type+"\n";
            String second = "version="+version+"\n";


            writer.write(first,0,first.length());
            writer.write(second,0,second.length());
            writer.flush();

            for(Stint st:stintSet){

                String line = "race=";
                double start = st.getStartTime();
                double end = st.getStartTime();
                double stintlen = end-start;
                double dist = st.getDistance();

                int stnumber = st.getNumber();
                int sthalf = st.getHalf();

                //missing metres travelled
                line += String.valueOf(dist)+","+String.valueOf(stintlen)+","+String.valueOf(start)+ "," + String.valueOf(end) + "," +"H"+String.valueOf(sthalf)+"S"+String.valueOf(stnumber)+"\n";
                writer.write(line,0,line.length());
                writer.flush();

            }
            String last = "IMFChecked=0\n";
            writer.write(last,0,last.length());
            writer.flush();
            writer.close();

        }
        catch(IOException e){
            e.printStackTrace();

        }

    }
    
    /**
     * Get the StintSet's i'th item.
     * @param i the index of the Stint item to retrieve.
     * @return 
     */
    public Stint getStint(int i) {
        return stintSet.get(i);
    }
    
    /**
     * Add a new Stint to the StintSet
     * @param stint the stint to be added to the set.
     */
    public void addStint(Stint stint) {
        stintSet.add(stint);
    }
}
