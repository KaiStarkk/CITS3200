package StintAnalyser.Analysis;

import StintAnalyser.Analysis.Heuristics.GPSAnalyser;
import StintAnalyser.Analysis.Heuristics.PlayerLoadAnalyser;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Data.GamePeriod;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.StintSet;

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
     * @return StintSet containing the final output information.
     */
    public boolean compile() {
        StintSet gpsResults = processGPS(this.dataSet, this.ground);
        filterStints(gpsResults);
        StintSet playerLoadResults = processPlayerLoad(this.dataSet);
        filterStints(playerLoadResults);
        
        StintSet finalStintSet = new StintSet();
        boolean succeeded = combine(finalStintSet, gpsResults, playerLoadResults);
        // fire blanks finalStintSet.writeToVid(outputPath + playerFile, dataSet.type, dataSet.version, gamePeriods.length);
        return succeeded;
    }

    private StintSet processGPS(DataSet dataSet, Ground ground) {
        GPSAnalyser gpsAnalyser = new GPSAnalyser(dataSet, ground);
        return gpsAnalyser.findStints();
    }

    private StintSet processPlayerLoad(DataSet dataSet) {
        PlayerLoadAnalyser playerLoad = new PlayerLoadAnalyser(dataSet);
        return playerLoad.findStints();
    }
    
    private void filterStints(StintSet stintSet) {
        // Remove any stint that falls outside the game periods
    }

    private boolean combine(StintSet finalStintSet, StintSet gpsResults, StintSet playerLoadResults) {
        return false;
    }
    
}
