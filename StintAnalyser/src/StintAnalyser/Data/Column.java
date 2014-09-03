/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Data;

import java.util.ArrayList;

/**
 *
 * @author Kieran
 */
public class Column {
    
    private ArrayList<Object> clist;
    
    /**
     * Constructor method
     * @param type A way of keeping track of what kind of data 
     * is being contained in the Column
     */
    public Column(int type){
        clist = new ArrayList<Object>();
    }
    /**
     * 
     * @param object The data point to add to the blah
     */
    public void add(Object object){
        clist.add(object);
    }
    
    public Object get(int index) {
	   return clist.get(index);
    }
    
    public int length() {
	   return clist.size();
    }
}
