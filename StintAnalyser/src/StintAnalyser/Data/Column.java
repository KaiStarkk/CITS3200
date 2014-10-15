/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Data;

import java.util.ArrayList;

/**
 * CITS3200 Professional Computing Column is a container to contain data for the analysis of Stints
 * @author Kieran
 */
public class Column<T> {

	private ArrayList<T> clist;

	/**
	 * Constructor method
	 * in the Column
	 */
	public Column() {
		clist = new ArrayList<T>();
		// TODO Auto-generated constructor stub
	}

        /**
         * Adds the object into the column
         * @param object the object to be inserted
         */
	public void add(T object) {
		clist.add(object);
	}

        /**
         * returns the object in the given column
         * @param index the position of the object to be searched for
         * @return the object
         */
	public T get(int index) {
		return clist.get(index);
	}

        /**
         * Extracts the amount of columns in the list
         * @return the size of the list
         */
	public int length() {
		return clist.size();
	}
        
         /**
	 * Determine the index of a known value
         * Only works with ints
	 *
         * @param value the Integer to be determined
	 * @return int index
	 */
        public int getIndex(Integer value){
            int index = 0;
        
            for(int i = 0; i < clist.size(); i++){
                T current = clist.get(i);
                int less = (Integer)current - 5;
                int more = (Integer)current + 5;
                //                                 <=               >
                /*current.equals(value) || */
                if((value.compareTo(more) <= 0) && (value.compareTo(less) > 0)){
                    index = i;
            }
            
        }
        
        return index;
    }
}