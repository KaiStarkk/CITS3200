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

/**
 *
 * @author Kieran
 */
public class DataSet {

	//vid file type
	//public String filename;
	public String type;

	//file version
	public String version;

	//possible set 0-time,1-gps,2-accelerometer
	//private Column[] columns;
	private Column<Integer> time;
	private Column<Double> load;
	private Column<Integer> gpstime;
	private Column<Double> gpslat;
	private Column<Double> gpslong;


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
			if (!check.equals("Time, Plyr. Load, GPS Time, GPS Latitude, GPS Longitude, ")) {
				//file invalid error 
				throw new IllegalArgumentException("Invalid Input due to having incorrect data fields");

			}

			String current;
			time = new Column<>();
			load = new Column<>();
			gpstime = new Column<>();
			gpslat = new Column<>();
			gpslong= new Column<>();

			//columns = new Column[5];

			while ((current = reader.readLine()) != null) {


				String[] contents = current.split(",");

				if (contents.length != 6) {

					throw new IllegalArgumentException("Invalid Input due to having an incorrect number of data fields");

				}

                //third element of contents
				

				time.add(convertTime(contents[0]));
				load.add(Double.parseDouble(contents[1]));
				String check2 = contents[2].trim();

				if (check2.equals("..")) {
					gpstime.add(-1);
					gpslat.add(-1.0);
					gpslong.add(-1.0);

				}
				else{

					gpstime.add(convertTime(contents[2].trim()));
					gpslat.add(Double.parseDouble(contents[3].trim()));
					gpslong.add(Double.parseDouble(contents[4].trim()));
				}
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
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
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
	/**
	 * Converts time in the form of HH:MM:SS.MS or MM:SS:MS to absolute milliseconds
	 *
	 * @param oldtime A time string in the form of HH:MM:SS.MS or MM:SS:MS 
	 * @return The input time in the the form of absolute milliseconds
	 */
	public int convertTime(String oldtime){
		int totaltime = 0;
		try{
			String[] splitter = oldtime.split(":");
			
			//MM:SS.MS
			if(splitter.length==2){
				//String part = (String)splitter[1];
				//int index = part.indexOf(".");
				//String part1 = part.substring(0,2);
				//String part2 = part.substring(3,part.length());
				//System.out.println("" + index);
				String[] endpart = splitter[1].split("\\.");
				totaltime += 60*Integer.parseInt(splitter[0])+Integer.parseInt(endpart[0]); //+ Integer.parseInt(endpart[1]);
				totaltime*=100;
				totaltime+=Integer.parseInt(endpart[1]);
				
				
			}
			else if(splitter.length==3){
				String[] endpart = splitter[2].split("\\.");

				//evaluating time in for HH:MM:SS
				if(endpart.length==1){
					totaltime += 60*60*Integer.parseInt(splitter[0]) + 60*Integer.parseInt(splitter[1]) + Integer.parseInt(splitter[2]);
					totaltime*=100;
				}
				// HH:MM:SS.MS
				else{
					totaltime += 60*60*Integer.parseInt(splitter[0]) + 60*Integer.parseInt(splitter[1]) + Integer.parseInt(endpart[0]);
					totaltime*=100;
					totaltime+= Integer.parseInt(endpart[1]);
				}

			}
			else{	
				throw new IllegalArgumentException("Invalid time parameters");
			}
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		return totaltime;
	}



}

