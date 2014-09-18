/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

/**
 *
 * @author Cameron
 */
public class JavaApplication1 {

    public static void main(String[] args) {

        Stint stint1 = new Stint(321563, 382859, 1, 1);
        Stint stint2 = new Stint(10, 100, 2, 1);

        StintSet stints = new StintSet();
        stints.addStint(stint1);
        stints.addStint(stint2);
        
        stints.writeToVid("", "LOGAN" , "5.1.3", 2);
        System.out.println("finished");

    }

}
