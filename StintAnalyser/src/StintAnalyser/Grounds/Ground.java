package StintAnalyser.Grounds;

/**
 * CITS3200 Professional Computing Grounds.java Contains fields and methods
 * pertaining to grounds. Grounds are rectangular game pitches.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Ground {
    
	private String name;
	private GPSCoordinate goal1;
	private GPSCoordinate goal2;
	private double aspectRatio;
    
        /**
         * Constructor for the Ground
         * @param name the name of the Ground
         * @param goal1 the coordinate of the 1st goal
         * @param goal2 the coordinate for the second goal
         * @param aspectRatio the aspect ratio of the pitch
         */
	public Ground(String name, GPSCoordinate goal1, GPSCoordinate goal2, double aspectRatio) {
		this.name = name;
		this.goal1 = goal1;
		this.goal2 = goal2;
		this.aspectRatio = aspectRatio;
	}
    
        /**
         * Transforms the Bearing
         * @return the bearing transformed
         */
	public double getTransformBearing() {
		return this.goal1.bearingTo(this.goal2);
	}
        
        /**
         * Returns the aspect ratio
         * @return the ratio
         */
	public double getAspectRatio() {
		return this.aspectRatio;
	}
        
        /**
         * Returns the length of the field
         * @return the field length
         */
	public double getFieldLength() {
		return this.goal1.distanceTo(this.goal2);
	}
        
        /**
         * Returns the half-way for the pitch
         * @return the length
         */
	public double getHalfFieldWidth() {
		return (this.getFieldLength() * this.getAspectRatio()) / 2;
	}
        
        /**
         * Returns the coordinate for the goal origin
         * @return the goal coordinate
         */
	public GPSCoordinate getOrigin() {
		return this.goal1;
	}
}
