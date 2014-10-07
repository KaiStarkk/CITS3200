/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Kieran
 */
public class Column<T> {

	private ArrayList<T> clist;

	/**
	 * Constructor method
	 *
	 * @param type A way of keeping track of what kind of data is being contained
	 * in the Column
	 */


	public Column() {
		clist = new ArrayList<T>();
		// TODO Auto-generated constructor stub
	}

	public void add(T object) {
		clist.add(object);
	}

	public T get(int index) {
		return clist.get(index);
	}

	public int length() {
		return clist.size();
	}
}