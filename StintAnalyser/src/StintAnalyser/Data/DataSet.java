/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StintAnalyser.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Kieran
 */
public class DataSet {

	//vid file type
	public String type;

	//file version
	public String version;

	//possible set 0-time,1-gps,2-accelerometer
	//private Column[] columns;
	private Column time;
	private Column load;
	private Column gpstime;
	private Column gpslat;
	private Column gpslong;

	/**
	 * Constructor. Constructs the array of columns which will then be passed to
	 * the Analyser.
	 *
	 * @param path the path to read .csv data from.
	 */
	public DataSet(String path) {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));

			String firstline = reader.readLine();
			String[] fsplit = firstline.split(" ");

			type = fsplit[0];
			version = fsplit[1];

			for (int i = 1; i < 7; i++) {
				reader.readLine();
			}
			String check = reader.readLine();
            //String checker = "Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longitude, /n";

			//exception will be changed
			if (!check.equals("Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longitude, /n")) {
				//file invalid error 
				throw new InvalidInputException("Invalid Input due to having incorrect data fields");

			}

			String current;
			//columns = new Column[5];
			this.time = new Column<Integer>();
			this.load = new Column<Double>();
			this.gpstime = new Column<Integer>();
			this.gpslat = new Column<Double>();
			this.gpslong = new Column<Double>();

			while ((current = reader.readLine()) != null) {

				String[] contents = current.split(",");

				if (contents.length > 5 || contents.length < 5) {

					throw new InvalidInputException("Invalid Input due to having an incorrect number of data fields");

				}

                //third element of contents
				String check2 = contents[2].trim();

				if (check2.equals("..")) {
					time.add(contents[0].trim());
					load.add(contents[1].trim());
					gpstime.add(-1);
					gpslat.add(-1);
					gpslong.add(-1);
					continue;

				}
				time.add(contents[0].trim());
				load.add(contents[1].trim());
				gpstime.add(contents[2].trim());
				gpslat.add(contents[3].trim());
				gpslong.add(contents[4].trim());

				/*for (int i = 0; i < 5; i++) {

					String content = contents[i].trim();
					columns[i].add(content);

				}*/

				

			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Encapsulates the task of determining which column corresponds to the
	 * relative time information in the DataSet, and returns that column.
	 *
	 * @return Column the column containing relative time data
	 */
	public Column getTimeColumn() {
		return time;
	}

	/**
	 * Encapsulates the task of determining which column corresponds to the player
	 * load information in the DataSet, and returns that column.
	 *
	 * @return Column the column containing player load data
	 */
	public Column getPlayerLoadColumn() {
		return load;
	}

	/**
	 * Encapsulates the task of determining which column corresponds to the GPS
	 * time information in the DataSet, and returns that column.
	 *
	 * @return Column the column containing GPS time data
	 */
	public Column getGPStimeColumn() {
		return gpstime;
	}

	/**
	 * Encapsulates the task of determining which column corresponds to the GPS
	 * latitude information in the DataSet, and returns that column.
	 *
	 * @return Column the column containing GPS latitude data
	 */
	public Column getGPSLatitudeColumn() {
		return gpslat;
	}

	/**
	 * Encapsulates the task of determining which column corresponds to the GPS
	 * longitude information in the DataSet, and returns that column.
	 *
	 * @return Column the column containing GPS longitude data
	 */
	public Column getGPSLongitudeColumn() {
		return gpslong;
	}

}
