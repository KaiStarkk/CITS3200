/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StintAnalyser.Data;

import java.nio.file.Path;

/**
 *
 * @author Kieran
 */
public class DataSet {
    
    /**
     * Constructor.
     * 
     * @param path the path to read .csv data from.
     */
    public DataSet(Path path) {
        
    }
    
    /**
     * Accessor for each column of the data.
     * @param colname
     * @return 
     */
    public Column getColumn(String colname) {
        return new Column();
    }
    
}
