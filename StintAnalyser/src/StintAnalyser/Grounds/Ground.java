package StintAnalyser.Grounds;

/**
 * CITS3200 Professional Computing
 * Grounds.java
 * Contains fields and methods pertaining to grounds. Grounds are rectangular
 * game pitches.
 *
 * @version 1.1 20/08/14
 * @author Group B
 */
public class Ground {
    
    String name;
    double[] goal1GPSCoord;
    double[] goal2GPSCoord;
    double aspectRatio;
    
    /**
     * Constructor.
     * @param name the name of the ground.
     * @param goal1GPSCoord the first goal, in latitude and longitude.
     * @param goal2GPSCoord the second goal, in latitude and longitude.
     * @param aspectRatio the aspect ratio of the ground.
     */
    public Ground(String name, double[] goal1GPSCoord, double[] goal2GPSCoord, double aspectRatio) {
        this.name = name;
        this.goal1GPSCoord = goal1GPSCoord;
        this.goal2GPSCoord = goal2GPSCoord;
        this.aspectRatio = aspectRatio;
    }
    
    /**
     * Converts the ground's coordinate system so that it is perpendicular
     * to the x-axis.
     * @return the rotation matrix to perform the operation.
     */
    public double[][] align() {
        return new double[][] {{1,1},{1,1}}; 
    }
}
