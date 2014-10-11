package StintAnalyser.Stints;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CITS3200 Professional Computing StintSet.java Contains fields and methods
 * pertaining to StintSets. StintSets are an array of Stints.
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
	 *
	 * @param path path to write the file to.
	 */
	    public void writeToVid(String path, String type, String version, int periods) {
        try {

            path = path.substring(0, path.lastIndexOf('.')) + ".vid";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            String first = "type=" + type.toUpperCase();
            String second = "version=" + version;
            writer.write(first, 0, first.length());
            writer.newLine();
            writer.write(second, 0, second.length());
            writer.newLine();
            writer.flush();
            
            char periodtype;
            if (periods == 2) {
                periodtype = 'H';
            } else if (periods == 4) {
                periodtype = 'Q';
            } else {
                periodtype = 'P';
            }
            
            for (Stint st : stintSet) {
                String line = "race=";
                int start = st.getStartTime();
                int end = st.getEndTime();
                float stintlen = ((float)end - (float)start)/100;
                
                int stnumber = st.getNumber();
                int stperiod = st.getPeriod();

                line += String.valueOf(stintlen) + ",0," + String.valueOf(start) + "," + String.valueOf(end) + ",\"" + periodtype + String.valueOf(stperiod) + "S" + String.valueOf(stnumber) + "\"";
                writer.write(line, 0, line.length());
                writer.newLine();
                writer.flush();
            }
            String last = "IMFChecked=0";
            writer.write(last, 0, last.length());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Get the StintSet's i'th item.
	 *
	 * @param i the index of the Stint item to retrieve.
	 * @return
	 */
	public Stint getStint(int i) {
		return stintSet.get(i);
	}

	/**
	 * Add a new Stint to the StintSet
	 *
	 * @param stint the stint to be added to the set.
	 */
	public void addStint(Stint stint) {
		stintSet.add(stint);
	}
	
        /**
         * Accessor for the size of the stintSet
         * @return the size of the stintSet
         */
	public int size() {
		return this.stintSet.size();
	}
        
        /**
         * Accessor for the stintSet
         * @return  the stint set
         */
        public ArrayList<Stint> getStints() {
            return stintSet;
        }
}
