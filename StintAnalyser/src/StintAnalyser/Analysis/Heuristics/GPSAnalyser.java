package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Stints.StintSet;

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
	
	public GPSAnalyser() {
		timeColumn = data.getColumn("time");
		latitudeColumn = data.getColumn(5);
		longitudeColumn = data.getColumn(4);
	}
	
	/**
	 * Determine if the player is playing or not.
	 *
	 * @param data The DataSet containing all game related time, gps and
	 * accelerometer data
	 * @param ground
	 * @return
	 */
	public StintSet getResult(DataSet data, GPSCoordinate goal1, GPSCoordinate goal2) {
		Column 

		double transformBearing = goal1.bearingTo(goal2);
		double fieldLength = goal1.distanceTo(goal2);
		double lenghtWidthRatio = 0.6; //Find length/width ratio dynamically
		double halfFieldWidth = (fieldLength * lenghtWidthRatio) / 2;

		for (int i = 0; i < timeColumn.length(); i++) {
			if (longitudeColumn.get(i) != "") {
				GPSCoordinate playerCoord = new GPSCoordinate(Double.parseDouble(latitudeColumn.get(i)), Double.parseDouble(longitudeColumn.get(i)));

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
