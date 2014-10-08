package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.StintSet;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Grounds.GPSCoordinate;
import StintAnalyser.Stints.Stint;

/**
 * CITS3200 Professional Computing GPSHeuristic.java Determines if a player is
 * playing on not depending on their GPS position.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class GPSAnalyser {

	private Column<Integer> timeColumn;
	private Column<Double> latitudeColumn;
	private Column<Double> longitudeColumn;

	private Ground ground;

	public GPSAnalyser(DataSet playerData, Ground ground) {
		this.timeColumn = playerData.getTimeColumn();
		this.latitudeColumn = playerData.getGPSLatitudeColumn();
		this.longitudeColumn = playerData.getGPSLongitudeColumn();

		this.ground = ground;
	}

	public StintSet findStints() {

		int lastKnown = 0;            //was the player on/off the field in the last time tic? 0 for off, 1 for on.
		int[] stintStarts = new int[100]; //an array to store the times that stints began
		int[] stintEnds = new int[100]; //an array to store the times that stints ended
		int stintNumber = 0;            //a counter to track which stint we are on at the moment

		for (int i = 0; i < this.timeColumn.length(); i++) {
			if (this.longitudeColumn.get(i) != 0 && this.latitudeColumn.get(i) != 0) { //the first column of player load time starts before the lat/long is recorded so we need to check that the lat/long isn't actually empty
				//Making the assumption that columns return doubles for lat/long data
				//Check with Alex/Cam how data should be stored in Column objects.
				double currentPlayerLatitude = this.latitudeColumn.get(i);
				double currentPlayerLongitude = this.longitudeColumn.get(i);

				GPSCoordinate playerCoord = new GPSCoordinate(currentPlayerLatitude, currentPlayerLongitude);
				playerCoord = playerCoord.rotate(playerCoord, this.ground.getTransformBearing());

				if (playerIsOnField(playerCoord)) { //check if we are on the field
					if (lastKnown == 0) { //check if we just came onto the field
						lastKnown = 1; //keep in mind for the next time index that we were already on the field
						stintStarts[stintNumber] = this.timeColumn.get(i);
					}
				}

				if (!playerIsOnField(playerCoord)) { //check if we are off the field
					if (lastKnown == 1) { //check of we just came off the fied
						lastKnown = 0; //keep in mind for the next time index that we were already off the field
						stintEnds[stintNumber++] = this.timeColumn.get(i);
					}
				}

			}
		}

		//I shall attempt to return stint sets. Emphasise on attempt - Ash
		StintSet GPSStintAttempts = new StintSet();
		for (int i = 0; i < stintNumber; i++) {
			GPSStintAttempts.addStint(new Stint(stintStarts[i], stintEnds[i], i, 1));
		}

		//figure out what goes in a StintSet
		return GPSStintAttempts;
	}

	private boolean playerIsOnField(GPSCoordinate player) {
            
            GPSCoordinate origin = this.ground.getOrigin();
		double xdisplacement = origin.horizontalDisplacementTo(player);
		double ydisplacement = Math.abs(origin.verticalDisplacementTo(player));

		return (xdisplacement < this.ground.getFieldLength() && xdisplacement > 0)
				&& (ydisplacement < this.ground.getHalfFieldWidth());
	}
}
