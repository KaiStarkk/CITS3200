package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Stints.StintSet;
import StintAnalyser.Data.DataSet;

/**
 * CITS3200 Professional Computing
 * AccelerometerHeuristic.java
 * Determines if a player is playing on not depending on their accelerometer data.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class AccelerometerHeuristic {

    /**
     * Determine if the player is playing or not.
     * @param accelerometerData
     * @return 
     */
    public static StintSet getResult(DataSet data) {


    	//experimental number will be changed to a calculated number
    	double sig_activity = 10;
    	boolean found_start = false;

    	Column time = data.getColumn(1);
    	Column accel = data.getColumn(2);
    	StintSet solution = new StintSet();


    	//check correct data
    	if(time.length()!=accel.length()){
    		return null;

    	}
    	double estimated_start=0;

    	int ticks = 0;
    	int tick_threshold_start = 100;
    	int tick_threshold_end = 100;
    	int stint_number = 1;

    	for(int i=0;i<time.length();i++){

    		double c_time = Double.parseDouble(time.get(i));
    		double c_accel = Double.parseDouble(accel.get(i));

    		if(found_start){
    			if(c_accel < sig_activity){

    				ticks++;
    				if(ticks>tick_threshold_end){

    					double end_time = Double.parseDouble(time.get(i-100));
    					solution.add(new Stint(estimated_start,end_time,0,))
    				}
    			}
    			else{

    				ticks=0;
    			}

    		}
    		else{

    			//continue
    		}

    	}


        return new StintSet();
    }
    
}
