package StintAnalyser.Analysis;

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
    private final StintSet specifiedResult;
    private final StintSet gpsResult;
    private final StintSet accelResult;
    private final int[][] gamePeriods;
    
    /**
     * Constructor.
     * @param gpsResult
     * @param accelResult
     * @param specifiedResult
     * @param gamePeriods
     */
    public Evaluator(StintSet specifiedResult, StintSet gpsResult, StintSet accelResult, int[][] gamePeriods){
        this.specifiedResult = specifiedResult;
        this.gpsResult = gpsResult;
        this.accelResult = accelResult;
        this.gamePeriods = gamePeriods;
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
    public StintSet compile() {
        return specifiedResult;
    }
}
