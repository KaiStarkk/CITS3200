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
				//Making the assumption that columns return doubles for lat/long data
				//Check with Alex/Cam how data should be stored in Column objects.
				double currentPlayerLatitude = (double)this.latitudeColumn.get(i);
				double currentPlayerLongitude = (double)this.longitudeColumn.get(i);
				
				GPSCoordinate playerCoord = new GPSCoordinate(currentPlayerLatitude, currentPlayerLongitude);
				playerCoord = playerCoord.rotate(playerCoord, ground.getTransformBearing());

				if (!playerIsOnField(playerCoord)) {
					//continue calculations
				}
			}
		}
		
		//figure out what goes in a StintSet
		return new StintSet();
	}

	private boolean playerIsOnField(GPSCoordinate player) {
		double xdisplacement = this.ground.getOrigin().horizontalDisplacementTo(player);
		double ydisplacement = Math.abs(this.ground.getOrigin().verticalDisplacementTo(player));

		return (xdisplacement < this.ground.getFieldLength() && xdisplacement > 0) 
						&& (ydisplacement < this.ground.getHalfFieldWidth());
	}
}
