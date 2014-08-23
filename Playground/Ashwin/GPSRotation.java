/* This class rotates a GPS coordinate about another to result is a bearing of 0.
 * That is, the second point will now lie directly 'East' of the first point.
 *
 * @version 1.0
 * @author Ashwin D'Cruz
 * @date 15.08.14
 */




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;


public class GPSRotation {
    
    public static void main(String[] args) {
        
        double orignalLatitude  = 0;
        double orignalLongtiude = 0;
        
        double rotatedLatitude  = 0;
        double rotatedLongitude = 0;
        
        double angleOfDifference = 0;
        //To get the angle of difference, apply Dean's bearing method.
        //Get the angle of one point relative to the other.
        
        
        /*To transform the latitude, this is the formula. Because we want the second point to lie directly east, we need an clockwise rotation. This only occurs with a negative angle hence the -1 in the sin argument*/
        rotatedLongitude = orignalLongtiude*(Math.cos(angleOfDifference)) - orignalLatitude*(Math.sin(-1*angleOfDifference));
        
        
        
        
        /*To transform the longitude, this is the formula. Again, we need a clockwise rotaion so negative angle.*/
        rotatedLatitude = orignalLongtiude*(Math.sin(-1*angleOfDifference)) + orignalLatitude*(Math.cos(angleOfDifference));
        
        //First apply this to the field coordinate. This will result in us having both points lying on the same latitude I think.
        //Then apply the same transformation to all the player data. THis will give us their 'new' point relative to this new frame of reference. Using this method. We now have coordinate bounds on all the longtidues.
        //I think we will need more points of the field to get the other two bounds. 
        
        
        
        
    }






}