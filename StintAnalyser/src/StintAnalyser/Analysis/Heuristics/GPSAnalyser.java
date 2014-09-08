package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.StintSet;
import stintanalyser.Grounds.Ground;
import stintanalyser.Grounds.GPSCoordinate;

/**
 * CITS3200 Professional Computing GPSHeuristic.java Determines if a player is
 * playing on not depending on their GPS position.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class GPSAnalyser {
	private Column timeColumn;
	private Column latitudeColumn;
	private Column longitudeColumn;
	
	private Ground ground;
	
	public GPSAnalyser(DataSet playerData, Ground ground) {
		this.timeColumn = playerData.getTimeColumn();
		this.latitudeColumn = playerData.getGPSLatitudeColumn();
		this.longitudeColumn = playerData.getGPSLongitudeColumn();
		
		this.ground = ground;
	}
	
	public StintSet findStints() {
		for (int i = 0; i < this.timeColumn.length(); i++) {
			if (this.longitudeColumn.get(i) != "") {
				double currentPlayerLatitude = Double.parseDouble(latitudeColumn.get(i));
				double currentPlayerLongitude = Double.parseDouble(longitudeColumn.get(i));
				
				GPSCoordinate playerCoord = new GPSCoordinate(currentPlayerLatitude, currentPlayerLongitude);

				playerCoord = playerCoord.rotate(playerCoord, transformBearing);

				if (!playerIsOnField(goal2, playerCoord, fieldLength, halfFieldWidth)) {
					//continue calculations
				}
			}
		}
		return new StintSet();
	}

	/**
	 *
	 *
	 */
	private boolean playerIsOnField(GPSCoordinate origin, GPSCoordinate player, double fieldLength, double halfFieldWidth) {
		double xdisplacement = origin.horizontalDisplacementTo(player);
		double ydisplacement = Math.abs(origin.verticalDisplacementTo(player));

		if ((xdisplacement < fieldLength && xdisplacement > 0) && ydisplacement < halfFieldWidth) {
			return true;
		} else {
			return false;
		}
	}
}
