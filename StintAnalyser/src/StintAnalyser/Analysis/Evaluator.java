package StintAnalyser.Analysis;

import StintAnalyser.Analysis.Heuristics.GPSAnalyser;
import StintAnalyser.Analysis.Heuristics.PlayerLoadAnalyser;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Data.GamePeriod;
import StintAnalyser.Data.GameTime;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.Stint;
import StintAnalyser.Stints.StintSet;

/**
 * CITS3200 Professional Computing
 * Evaluator.java
 * Weighs the heuristic results and returns a final answer.
 * Takes input from the DataSet, to determine whether a player
 * is playing on the field at a certain time during the game or not.
 * Makes statistical observations to determine the quality of the results,
 * and incorporates this into the answer.
 *
 * @version 1.5 10/10/14
 * @author Marcus and Kieran
 */
public class Evaluator {
    
    String outputPath;
    String playerFile;
    Ground ground;
    GameTime startTime;
    GamePeriod[] gamePeriods;
    DataSet dataSet;
    int tolerance = 5000; //tolerance level
    double playerLoadTolerance = 0.5;

    
    /**
     * Constructor.
     * @param outputPath the output path for output of .vid file
     * @param playerFile the input path of the player file
     * @param ground the ground to be used in analysis
     * @param startTime
     * @param gamePeriods an array of Gameperiod for the current match
     */
    public Evaluator(String outputPath, String playerFile, Ground ground, GameTime startTime, GamePeriod[] gamePeriods){
        this.outputPath = outputPath;
        this.playerFile = playerFile;
        this.ground = ground;
        this.gamePeriods = gamePeriods;
        this.startTime = startTime;
        this.dataSet = new DataSet(outputPath + playerFile);
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
        //filteredStints = gpsResults;
        combinedStints = combine(gpsResults);
        filteredStints = filter(combinedStints);
        
        filteredStints.writeToVid(outputPath + playerFile, dataSet.type, dataSet.version, gamePeriods.length);
    }

    /**
     * Processes the stints wrt the GPS players are on field
     * @param dataSet takes in the players data
     * @param ground takes in the grounds data
     * @return returns a StintSet with stint start and end relative to when players go on/off the pitch
     */
    private StintSet processGPS(DataSet dataSet, Ground ground) {
        GPSAnalyser gpsAnalyser = new GPSAnalyser(dataSet, ground);
        return gpsAnalyser.findStints();
    }
    
    /**
     * Filters the stints by removing stints that are outside of the given playing times
     * @param stintSet the stintSet to be filtered
     * @return returnSet the final stintSet
     */
    private StintSet filter(StintSet stintSet) {
        StintSet returnSet = new StintSet();
        
        int periodNumber = 0;
        int timerStart = dataSet.convertTime(dataSet.timerStart);
        int gameStart = dataSet.convertTime(startTime.toString());
        
        for (GamePeriod gamePeriod : gamePeriods) {
            int number = 1;
            int periodStart = dataSet.convertTime(gamePeriod.getStart().toString()) - timerStart + gameStart;
            int periodEnd = dataSet.convertTime(gamePeriod.getEnd().toString()) - timerStart + gameStart;
            periodNumber++;
            for (Stint stint : stintSet.getStints()) {
                
                if (stint.getEndTime() > periodStart && stint.getEndTime() < periodEnd) {
                    if (stint.getStartTime() > periodStart) {
                        returnSet.addStint(new Stint(stint.getStartTime(), stint.getEndTime(), number, periodNumber));
                        number++;
                    } else {
                        returnSet.addStint(new Stint(periodStart, stint.getEndTime(), number, periodNumber));
                        number++;
                    }
                } else if (stint.getStartTime() < periodEnd && stint.getStartTime() > periodStart) {
                    if (stint.getEndTime() > periodEnd) {
                        returnSet.addStint(new Stint(stint.getStartTime(), periodEnd, number, periodNumber));
                        number++;
                    }
                } else if (stint.getStartTime() < periodStart && stint.getEndTime() > periodEnd) {
                        returnSet.addStint(new Stint(periodStart, periodEnd, number, periodNumber));
                        number++;
                }
            }
        }
        
        return returnSet;
    }

    /**
     * Combines stints that have low in-between stint times and checks for activity in these periods
     * @param gpsResults
     * @return the combined stintSet 
     */
    private StintSet combine(StintSet gpsResults) {
        
        StintSet returnSet = new StintSet();
        int startCount = 0;
        int stintIndex = 0;
        
        while(stintIndex < (gpsResults.size() - 1)) {    
            
            while(
					(stintIndex < (gpsResults.size() - 1)) && 
					((gpsResults.getStint(stintIndex+1).getStartTime() 
					- gpsResults.getStint(stintIndex).getEndTime()) < tolerance)
			) {    
				//this is adding in the fuctionality of the playerload
                    stintIndex++;
            }
            PlayerLoadAnalyser playerLoad;
            playerLoad = new PlayerLoadAnalyser(dataSet);
			
            Stint temp = new Stint(gpsResults.getStint(startCount).getStartTime(), gpsResults.getStint(stintIndex).getEndTime(),0,0);
			if(playerLoad.average(temp.getStartTime(), temp.getEndTime())>playerLoadTolerance) {
				returnSet.addStint(temp);
			}
            stintIndex++;
            startCount = stintIndex;       
        }
		
        // Add last stint
        if (gpsResults.size() != 0) {
			stintIndex--;
			startCount--;
            Stint temp = new Stint(gpsResults.getStint(startCount).getStartTime(), gpsResults.getStint(stintIndex).getEndTime(),0,0);
            returnSet.addStint(temp);
        }
        
        return returnSet;
    }
    
}
