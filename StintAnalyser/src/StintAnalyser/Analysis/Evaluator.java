package StintAnalyser.Analysis;

import StintAnalyser.Analysis.Heuristics.GPSAnalyser;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Data.GamePeriod;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.StintSet;
import StintAnalyser.Stints.Stint;

/**
 * CITS3200 Professional Computing
 * Evaluator.java
 * Weighs the heuristic results and returns a final answer.
 * Takes input from the {@link GPSHeuristic}, {@link TimeHeuristic}
 * and {@link AccelerometerHeuristic}, to determine whether a player
 * is playing on the field at a certain time during the game or not.
 * Makes statistical observations to determine the quality of the results,
 * and incorporates this into the answer.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Evaluator {
    
    String outputPath;
    String playerFile;
    Ground ground;
    GamePeriod[] gamePeriods;
    DataSet dataSet;
    int tolerance = 1000; //tolerance level

    
    /**
     * Constructor.
     * @param outputPath
     * @param playerFile
     * @param ground
     * @param gamePeriods
     */
    public Evaluator(String outputPath, String playerFile, Ground ground, GamePeriod[] gamePeriods){
        this.outputPath = outputPath;
        this.playerFile = playerFile;
        this.ground = ground;
        this.gamePeriods = gamePeriods;
        this.dataSet = new DataSet(outputPath);
    }
    
    /**
     * Evaluates statistical relationships between the three results to
     * determine the programs output.
     * Currently, this means returning the user-specified periods. In the future,
     * we can make it take the average of the three StintSets.
     * In later releases, this can be improved by looking at the standard
     * deviation as well as the six possible pairs of results to try and
     * determine if one is an outlier. We can also associate a certain confidence
     * based on the variation between our result and the periods specified.
     */
    public void compile() {
        StintSet gpsResults = processGPS(this.dataSet, this.ground);
        
        StintSet combinedStints;
        StintSet filteredStints;
        
        combinedStints = combine(gpsResults);
        filteredStints = filter(combinedStints);
        
        // *fire blanks* filteredStints.writeToVid(outputPath + playerFile, dataSet.type, dataSet.version, gamePeriods.length);
    }

    private StintSet processGPS(DataSet dataSet, Ground ground) {
        GPSAnalyser gpsAnalyser = new GPSAnalyser(dataSet, ground);
        return gpsAnalyser.findStints();
    }
    
    private StintSet filter(StintSet stintSet) {
        StintSet returnSet = new StintSet();
        
        int periodNumber = 0;
        
        for (GamePeriod gamePeriod : gamePeriods) {
            int number = 1;
            int periodStart = dataSet.convertTime(gamePeriod.getStart().toString());
            int periodEnd = dataSet.convertTime(gamePeriod.getEnd().toString());
            periodNumber++;
            for (Stint stint : stintSet.getStints()) {
                
                if (stint.getEndTime() > periodStart) {
                    if (stint.getStartTime() > periodStart) {
                        returnSet.addStint(new Stint(stint.getStartTime(), stint.getEndTime(), number, periodNumber));
                        number++;
                    } else {
                        returnSet.addStint(new Stint(periodStart, stint.getEndTime(), number, periodNumber));
                        number++;
                    }
                } else if (stint.getStartTime() < periodEnd) {
                    if (stint.getEndTime() > periodEnd) {
                        returnSet.addStint(new Stint(stint.getStartTime(), periodEnd, number, periodNumber));
                        number++;
                    }
                } else if (stint.getStartTime() < periodStart
                        && stint.getEndTime() > periodEnd) {
                        returnSet.addStint(new Stint(periodStart, periodEnd, number, periodNumber));
                        number++;
                }
            }
        }
        
        return returnSet;
    }

    /**
     * Combines stints that have low in-between stint times 
     * @param gpsResults
     * @return the combined stintSet 
     */
    private StintSet combine(StintSet gpsResults) {
        
        StintSet returnSet = new StintSet();
        int startCount = 0;
        int stintIndex = 0;
        
        while(stintIndex < gpsResults.size() - 1) {    
            //need to add check for playerload here too
            while((gpsResults.getStint(stintIndex+1).getStartTime() - gpsResults.getStint(stintIndex).getEndTime()) < tolerance) {              
                 stintIndex++;
            }
            
            Stint temp = new Stint(gpsResults.getStint(startCount).getStartTime(), gpsResults.getStint(stintIndex).getEndTime(),0,0);
            returnSet.addStint(temp);
            stintIndex++;
            startCount = stintIndex;       
        }
        // Add last stint
        if (gpsResults.size() != 0) {
            Stint temp = new Stint(gpsResults.getStint(startCount).getStartTime(), gpsResults.getStint(stintIndex).getEndTime(),0,0);
            returnSet.addStint(temp);
        }
        
        /*
        
        for(int i = 1; i<gpsResults.size(); i++) {
            lastEnd = returnSet.getStint(numberCount).getEndTime();
            nextStart = gpsResults.getStint(i+1).getStartTime();
            if(nextStart-lastEnd>tolerance) {
                //checking for player load as well
                Stint newStint = new Stint(gpsResults.getStint(i).getStartTime(), gpsResults.getStint(i+1).getEndTime(), numberCount, 0);
                
            }
            else {
                returnSet.addStint(gpsResults.getStint(i));
                numberCount++;
            }
        }  
        */
        return returnSet;
    }
    
}
