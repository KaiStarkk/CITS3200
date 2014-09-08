package stintanalyser.Grounds;

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

	public Ground(String name, GPSCoordinate goal1, GPSCoordinate goal2, double aspectRatio) {
		this.name = name;
		this.goal1 = goal1;
		this.goal2 = goal2;
		this.aspectRatio = aspectRatio;
	}

	public double getTransformBearing() {
		return this.goal1.bearingTo(this.goal2);
	}

	public double getAspectRatio() {
		return this.aspectRatio;
	}

	public double getFieldLength() {
		return this.goal1.distanceTo(this.goal2);
	}

	public double getHalfFieldWidth() {
		return (this.getFieldLength() * this.getAspectRatio()) / 2;
	}

	public GPSCoordinate getOrigin() {
		return this.goal1;
	}
}
