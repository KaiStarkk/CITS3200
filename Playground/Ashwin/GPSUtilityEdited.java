/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Dean 
 */
class GPSCoordinate {

    //need to be in Radians for calculations
    private double latitude;
    private double longitude;

    public GPSCoordinate(double latitude, double longitude) {
	if (latitude < -90 || latitude > 90) {
	    throw new IllegalArgumentException(
		    "Inavlid latitude: " + latitude);
	}
	if (longitude < -180.00 || longitude > 180.00) {
	    throw new IllegalArgumentException(
		    "Invalid longitude: " + longitude);
	}

	this.latitude = Math.toRadians(latitude);
	this.longitude = Math.toRadians(longitude);
    }

    /**
     * Returns latitude of GPS Coordinate in radians
     * @return latitude value in radians
     */
    public double latitude() {
	return this.latitude;
    }

     /**
     * Returns longitude of GPS Coordinate in radians
     * @return longitude value in radians
     */
    public double longitude() {
	return this.longitude;
    }

    /**
     * Returns the "greater circle" distance between this GPS coordinate and the
     * other GPS coordinate over 'there' in meters.
     * @param there
     * @return double "greater circle" distance to another point in meters
     */
    public double distanceTo(GPSCoordinate there) {
	double distance;

	double R = 6371000.00; //m

	double deltaLat = there.latitude - this.latitude;
	double deltaLong =  there.longitude - this.longitude;

	double haversine = Math.pow(Math.sin(deltaLat / 2), 2)
		+ Math.cos(this.latitude) * Math.cos(there.latitude)
		* Math.pow(Math.sin(deltaLong / 2), 2);

	distance = R * 2 * Math.atan2(
		Math.sqrt(haversine), Math.sqrt(1 - haversine));

	return distance;
    }

    /**
     * Calculates the bearing between this GPS coordinate and the other GPS
     * Coordinate over 'there' in radians on the unit circle where 0 radians
     * is exactly due East, and Math.PI/2 radians is exactly due North.
     * @param there the other GPS Coordinate
     * @return the bearing to 'there' in radians
     */
    public double bearingTo(GPSCoordinate there) {
	double bearing;
	
	double deltaLong =  there.longitude - this.longitude;
	
	double part1 =	Math.sin(deltaLong) * Math.cos(there.latitude);
	double part2 =	Math.cos(this.latitude) * Math.sin(there.latitude) - 
			Math.sin(this.latitude) * Math.cos(there.latitude) *
			Math.cos(deltaLong);
	
	bearing = Math.atan2(part1, part2);
	bearing = (bearing + (2 * Math.PI)) % (2 * Math.PI);
	
	return bearing;
    }
    
    /**
     * Rotates a GPS coordinate with respect to another GPS coordinate based on the
     * bearing between them, before rotation.
     * Rotation is clockwise.
     * @param bearing the bearing between the two points before rotation.
     * @return the transformed (rotated) GPS coordinate.
     */
    public GPSCoordinate rotate(double bearing) {
        GPSCoordinate afterRotation;
        
        double originalLatitude  = this.latitude;
        double originalLongitude = this.longitude;
        
        afterRotation.longitude = originalLongitude * Math.cos(bearing) -
                                 originalLatitude * Math.sin(-1 * bearing);
        
        afterRotation.latitude = originalLongitude * Math.sin(-1 * bearing) +
                                  originalLatitude * Math.cos(bearing);
        
        return afterRotation;
    }
    
    /**
     * Calculates the horizontal displacement between this GPS coordinate
     * and the other GPS coordinate over 'there' in meters. 
     * @param  there the other GPS coordinate
     * @return the horizontal displacement to 'there' in meters
     */
    public double horizontalDisplacementTo(GPSCoordinate there) {
        double horizontalDisplacement;
        
        double bearingToThere  = this.bearingTo(there);
        double distanceToThere = this.distanceTo(there);
        
        horizontalDisplacement = distanceToThere * Math.cos(bearingToThere);
        
        return horizontalDisplacement;
    }
    
    /**
     * Calculates the vertical displacement between this GPS coordinate
     * and the other GPS coordinate over 'there' in meters.
     * @param  there the other GPS coordinate
     * @return the horizontal displacement to 'there' in meters
     */
    public double verticalDisplacementTo(GPSCoordinate there) {
        double verticalDisplacement;
        
        double bearingToThere  = this.bearingTo(there);
        double distanceToThere = this.distanceTo(there);
        
        verticalDisplacement = distanceToThere * Math.sin(bearingToThere);
        
        return verticalDisplacement;
        
    }
}
