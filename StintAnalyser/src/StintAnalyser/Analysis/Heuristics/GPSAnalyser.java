package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.StintSet;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Grounds.GPSCoordinate;
import StintAnalyser.Stints.Stint;
import java.util.ArrayList;
import java.util.List;

/**
 * CITS3200 Professional Computing GPSHeuristic.java Determines if a player is
 * playing on not depending on their GPS position.
 *
 * @version 1.1 20/08/14
 * @author Ashwin and Dean
 */
public class GPSAnalyser {
        
	private Column<Integer> timeColumn;
	private Column<Double> latitudeColumn;
	private Column<Double> longitudeColumn;

	private Ground ground;
        
        /**
         * Constructor for GPS Analyser
         * @param playerData the DataSet holding all the data from .csv file
         * @param ground the ground structure that holds information for the current ground in use
         */
	public GPSAnalyser(DataSet playerData, Ground ground) {
		this.timeColumn = playerData.getTimeColumn();
		this.latitudeColumn = playerData.getGPSLatitudeColumn();
		this.longitudeColumn = playerData.getGPSLongitudeColumn();

		this.ground = ground;
	}
    
        /**
         * findStints finds and breaks up the data into small stints corresponding to times where players go on/off the pitch
         * @return the StintSet containing all stints after broken up
         */
	public StintSet findStints() {

		int playerOnDuringLastPeriod = 0;
		ArrayList<Integer> stintStarts = new ArrayList<>();
		ArrayList<Integer> stintEnds = new ArrayList<>();
		int stintNumber = 0;            

		for (int i = 0; i < this.timeColumn.length(); i++) {
			if (this.longitudeColumn.get(i) != 0 && this.latitudeColumn.get(i) != 0) { //the first column of player load time starts before the lat/long is recorded so we need to check that the lat/long isn't actually empty
				//Making the assumption that columns return doubles for lat/long data
				//Check with Alex/Cam how data should be stored in Column objects.
				double currentPlayerLatitude = this.latitudeColumn.get(i);
				double currentPlayerLongitude = this.longitudeColumn.get(i);

				GPSCoordinate origin = this.ground.getOrigin();
				double transformationBearing = this.ground.getTransformBearing();
				GPSCoordinate playerCoord = new GPSCoordinate(currentPlayerLatitude, currentPlayerLongitude);
				
				playerCoord = origin.rotate(playerCoord, transformationBearing);

				if (playerIsOnField(playerCoord)) { //check if we are on the field
					if (playerOnDuringLastPeriod == 0) { //check if we just came onto the field
						playerOnDuringLastPeriod = 1; //keep in mind for the next time index that we were already on the field
						stintStarts.add(stintNumber, this.timeColumn.get(i));
					}
				}

				if (!playerIsOnField(playerCoord)) { //check if we are off the field
					if (playerOnDuringLastPeriod == 1) { //check of we just came off the fied
						playerOnDuringLastPeriod = 0; //keep in mind for the next time index that we were already off the field
						stintEnds.add(stintNumber++, this.timeColumn.get(i));
					}
				}

			}
		}

		//I shall attempt to return stint sets. Emphasise on attempt - Ash
		StintSet GPSStintAttempts = new StintSet();
		for (int i = 0; i < stintNumber; i++) {
			GPSStintAttempts.addStint(new Stint(stintStarts.get(i), stintEnds.get(i), 0, 0));
		}

		//figure out what goes in a StintSet
		return GPSStintAttempts;
	}

        /**
         * playerIsOnField checks for a player being on the field
         * @param player the coordinate of the player
         * @return true iff the player is on the pitch at the given time
         */
	private boolean playerIsOnField(GPSCoordinate player) {
            
		GPSCoordinate origin = this.ground.getOrigin();
		//origin = origin.rotate(playerCoord, this.ground.getTransformBearing());
		double xdisplacement = origin.horizontalDisplacementTo(player);
		double ydisplacement = Math.abs(origin.verticalDisplacementTo(player));
                double fieldLength = this.ground.getFieldLength();
                double halfFieldWidth = this.ground.getHalfFieldWidth();
                
                
		return (xdisplacement < fieldLength && xdisplacement > 0)
				&& (ydisplacement < halfFieldWidth);
	}
}
