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
public class Field {
    private GPSCoordinate alphaCoordinate;
    private GPSCoordinate betaCoordinate;
    private double length;
    private double width;
    
    public Field(GPSCoordinate alphaCoord, GPSCoordinate betaCoord, double length, double width) {
        this.alphaCoordinate = alphaCoord;
        this.betaCoordinate = betaCoord;
        this.length = length;
        this.width = width;
    }
    
    
}
