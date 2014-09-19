package StintAnalyser.Analysis.Heuristics;

import StintAnalyser.Data.Column;
import StintAnalyser.Data.DataSet;
import StintAnalyser.Stints.StintSet;
import StintAnalyser.Grounds.Ground;
import StintAnalyser.Grounds.GPSCoordinate;

/**
 * CITS3200 Professional Computing GPSHeuristic.java Determines if a player is
 * playing on not depending on their GPS position.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class GPSAnalyser {
	private Column<Integer>    timeColumn;
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
            
            int   offStart     = 0;            //keep track of the start of an off period. the end of a stint as it were.
            int   onStart      = 0;            //keep track of the start of an on period. the start of a stint as it were.
            int   lastKnown    = 0;            //was the player on/off the field in the last time tic? 0 for off, 1 for on.
            int   onCounter    = 0;            //keep track of how long the player has been on the field
            int   offCounter   = 0;            //keep track of how long the player has been off the field
            int[] stintStarts  = new int[100]; //an array to store the times that stints began
            int[] stintEnds    = new int[100]; //an array to store the times that stints ended
            int   stintNumber  = 0;            //a counter to track which stint we are on at the moment
            
            
		for (int i = 0; i < this.timeColumn.length(); i++) {
			if (this.longitudeColumn.get(i) != 0 && this.latitudeColumn.get(i) != 0 ) { //the first column of player load time starts before the lat/long is recorded so we need to check that the lat/long isn't actually empty
				//Making the assumption that columns return doubles for lat/long data
				//Check with Alex/Cam how data should be stored in Column objects.
				double currentPlayerLatitude = (double)this.latitudeColumn.get(i);
				double currentPlayerLongitude = (double)this.longitudeColumn.get(i);
				
				GPSCoordinate playerCoord = new GPSCoordinate(currentPlayerLatitude, currentPlayerLongitude);
				playerCoord = playerCoord.rotate(playerCoord, this.ground.getTransformBearing());

				if (playerIsOnField(playerCoord)) { //check if we are on the field
                                    if( lastKnown == 0 ) { //check if we just came onto the field
                                            onStart    = this.timeColumn.get(i); //note this as a possible stint start
                                            lastKnown  = 1; //keep in mind for the next time index that we were already on the field
                                            onCounter  = 1; //note that we have now been on the field for one time tic
                                    }
                                    
                                    if( lastKnown == 1) { //check if we were on the field during the last time tic
                                            onCounter++;  //incrememt the counter that tells us how many time tics we have been on the field
                                    }
                                    
                                    if( onCounter == 300) { //check if we have been on the field for 300 time tics
                                        stintStarts[stintNumber] = onCounter; //note down the 1st time tic we came on as a start of a stint
                                    }
				}
                                
                                if (!playerIsOnField(playerCoord)) { //check if we are off the field
                                    if( lastKnown == 1 ) { //check of we just came off the fied
                                            onStart     = this.timeColumn.get(i); //note this as a possible stint end
                                            lastKnown   = 0; //keep in mind for the next time index that we were already off the field
                                            offCounter  = 1; //note that we have now been off the field for one time tic
                                    }
                                    
                                    if( lastKnown == 0) { //check if we were off the field during the last time tic
                                            offCounter++; //increment the counter that tells us how many time tics we have been off the field
                                    }
                                    
                                    if( offCounter == 300) { //check if we have been off the field for 300 time tics
                                        stintEnds[stintNumber++] = offCounter; //note the the 1st time tic we came off as an end of a stint
                                    }
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
